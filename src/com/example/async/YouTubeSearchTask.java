package com.example.async;

import org.apache.http.HttpStatus;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.SearchListAdapter;
import com.example.data.Feed;
import com.example.data.FeedFactory;
import com.example.web.HttpServerIF;
import com.example.youtubesearch.R;


public class YouTubeSearchTask extends AsyncTask<String, Void, Integer>
{
	private static final String YOUTUBE_URL = "http://gdata.youtube.com/feeds/api/videos?";
	private static final String PARAMS = "alt=atom&max-results=10&start-index=";
	private Context mContext;
	private ProgressDialog mDialog = null;
	private ListView mList;
	private TextView mText;
	private Feed mFeed;

	public YouTubeSearchTask(Context context, ListView list, TextView txt)
	{
		mContext = context;
		mList = list;
		mText = txt;
	}

	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();

		mDialog = new ProgressDialog(mContext);
		mDialog.setMessage(mContext.getString(R.string.dialog_receiving));
		mDialog.setIndeterminate(true);
		mDialog.show();
	}

	@Override
	protected Integer doInBackground(String... arg0)
	{
		HttpServerIF svr = new HttpServerIF();
		int iRet = svr.requestText(YOUTUBE_URL + PARAMS + arg0[1] + "&vq=" + arg0[0]);

		if(iRet == HttpStatus.SC_OK)
		{
			FeedFactory factory = new FeedFactory();
			mFeed = (Feed)factory.create(svr.getResText());
		}

		return new Integer(iRet);
	}

	@Override
	protected void onPostExecute(Integer result)
	{
		super.onPostExecute(result);

		if(result == HttpStatus.SC_OK)
		{
			//成功したのでデータを表示
			if(mFeed != null)
			{
				SearchListAdapter adapter = new SearchListAdapter(mContext, mFeed.getEntry());
				mList.setAdapter(adapter);
				mText.setText(String.format("%d / %d" + mContext.getString(R.string.page_unit), mFeed.getStartIndex(), mFeed.getTotalResults()));
			}
		}

		mDialog.dismiss();
	}

}
