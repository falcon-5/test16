package com.example.youtubesearch;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.async.YouTubeSearchTask;
import com.example.data.Entry;
import com.example.image.ImageCache;

public class SearchActivity extends ListActivity
{
	private YouTubeSearchTask mSearchTask = null;
	private String mKeyword;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result_list);

		Bundle extras = getIntent().getExtras();
		if(extras != null)
		{
			if(extras.containsKey("keyword"))
			{
				mKeyword = extras.getString("keyword");
			}
		}
	}

	@Override
	protected void onResume()
	{
		super.onResume();

		ListView list = (ListView)findViewById(android.R.id.list);
		TextView txt = (TextView)findViewById(R.id.txtCount);
		mSearchTask = new YouTubeSearchTask(this, list, txt);
		mSearchTask.execute(Uri.encode(mKeyword), "1");
	}

	@Override
	protected void onPause()
	{
		super.onPause();

		if(mSearchTask != null && !mSearchTask.isCancelled())
		{
			mSearchTask.cancel(true);
		}
	}

	@Override
	protected void onDestroy()
	{
		//TODO: 自動生成されたメソッド・スタブ
		super.onDestroy();
		//キャッシュ画像の解放
		ImageCache.clear();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id)
	{
		super.onListItemClick(l, v, position, id);

		Entry entry = (Entry)l.getItemAtPosition(position);
		if(entry != null)
		{
			String url = entry.getGroup().getContent().get(0).getUrl();
			if(url.trim().length() > 0)
			{
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setData(Uri.parse(url));
				startActivity(intent);
			}
		}
	}

}
