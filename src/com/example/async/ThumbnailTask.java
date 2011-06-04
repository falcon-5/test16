package com.example.async;

import org.apache.http.HttpStatus;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.image.ImageCache;
import com.example.web.HttpServerIF;

public class ThumbnailTask extends AsyncTask<String, Void, Integer>
{
	private Bitmap mBitmap = null;
	private ImageView mImage = null;

	public ThumbnailTask(ImageView image)
	{
		mImage = image;
	}

	@Override
	protected Integer doInBackground(String... params)
	{
		String url = params[0];
		int iRet = 0;
		if(url != null && url.length() > 0)
		{
			HttpServerIF svr = new HttpServerIF();
			iRet = svr.requestImage(url);
			if(iRet == HttpStatus.SC_OK)
			{
				mBitmap = svr.getResBitmap();
				if(mBitmap != null)
				{
					ImageCache.setImage(params[1], mBitmap);
				}
			}
		}
		return new Integer(iRet);
	}

	@Override
	protected void onPostExecute(Integer result)
	{
		super.onPostExecute(result);
		if(result == HttpStatus.SC_OK)
		{
			mImage.setImageBitmap(mBitmap);
		}
	}
}
