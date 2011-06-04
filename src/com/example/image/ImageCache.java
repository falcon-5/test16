package com.example.image;

import java.util.HashMap;
import java.util.Iterator;

import android.graphics.Bitmap;

public class ImageCache
{
	private static HashMap<String, Bitmap> mImageMap = null;

	public static Bitmap getImage(String key)
	{
		Bitmap bmp = null;
		if(mImageMap != null)
		{
			if(mImageMap.containsKey(key))
			{
				bmp = mImageMap.get(key);
			}
		}

		return bmp;
	}

	public static void setImage(String key, Bitmap bmp)
	{
		if(mImageMap == null)
		{
			mImageMap = new HashMap<String, Bitmap>();
		}
	}

	public static void clear()
	{
		if(mImageMap != null)
		{
			//画像データのクリア
			Iterator<String> it = mImageMap.keySet().iterator();
			while(it.hasNext())
			{
				String key = it.next();
				Bitmap bmp = mImageMap.get(key);
				if(bmp != null)
				{
					bmp.recycle();
					bmp = null;
				}
			}
		}

		//マッピング情報の消去
		mImageMap.clear();
	}

}
