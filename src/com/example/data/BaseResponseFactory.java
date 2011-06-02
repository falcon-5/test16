package com.example.data;

import java.io.StringReader;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import android.util.Xml;

public abstract class BaseResponseFactory
{
	protected String mText;			//TEXT時にテキストを退避しておく変数

	public BaseData create(String strResponse)
	{
		//XML文字列を解析
		parse(strResponse);

		//結果を返す
		return getResponse();
	}

	private void parse(String strResponse)
	{
		if(strResponse != null && strResponse.length() > 0)
		{
			try
			{
				XmlPullParser parser = Xml.newPullParser();
				parser.setInput(new StringReader(strResponse));

				int parseType;
				while((parseType = parser.next()) != XmlPullParser.END_DOCUMENT)
				{
					switch(parseType)
					{
					case XmlPullParser.START_TAG:
						onStartTag(parser);
						break;

					case XmlPullParser.TEXT:
						mText = parser.getText();
						break;

					case XmlPullParser.END_TAG:
						onEndTag(parser);
						break;

					}
				}
			}
			catch(Exception e)
			{
				Log.d("ResponseFactory", "", e);
			}
		}
	}

	//型変換などに利用するメソッド
	protected int convertInt(String strValue)
	{
		int iRet = 0;
		if(strValue != null)
		{
			iRet = Integer.parseInt(strValue);
		}
		return iRet;
	}
	protected long convertLong(String strValue)
	{
		long lRet = 0;
		if(strValue != null)
		{
			lRet = Long.parseLong(strValue);
		}
		return lRet;
	}
	protected double convertDouble(String strValue)
	{
		double dRet = 0.0;
		if(strValue != null)
		{
			dRet = Double.parseDouble(strValue);
		}
		return dRet;
	}

	protected String getAttribute(XmlPullParser parser, String strName)
	{
		String strRet = null;
		for(int i=0; i<parser.getAttributeCount(); i++)
		{
			if(parser.getAttributeName(i).equals(strName))
			{
				strRet = parser.getAttributeValue(i);
			}
		}
		return strRet;
	}

	//以下のクラスを子クラスに実装してもらう。
	protected abstract void onStartTag(XmlPullParser parser) throws Exception;
	protected abstract void onEndTag(XmlPullParser parser);
	protected abstract BaseData getResponse();
}
