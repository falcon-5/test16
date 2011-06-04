package com.example.adapter;

import java.util.List;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.async.ThumbnailTask;
import com.example.data.Entry;
import com.example.image.ImageCache;
import com.example.youtubesearch.R;

public class SearchListAdapter extends ArrayAdapter<Entry>
{
	private LayoutInflater mInflater;

	public SearchListAdapter(Context context, List<Entry> objects)
	{
		this(context, 0, objects);
	}

	public SearchListAdapter(Context context, int textViewResourceId, List<Entry> objects)
	{
		super(context, textViewResourceId, objects);
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder holder = new ViewHolder();

		Entry entry = this.getItem(position);
		if(convertView == null)
		{
			convertView = mInflater.inflate(R.layout.result_list_layout, null);

			holder.image = (ImageView)convertView.findViewById(R.id.imgThumbnail);
			holder.txt_title = (TextView)convertView.findViewById(R.id.txt_Title);
			holder.txt_author = (TextView)convertView.findViewById(R.id.txt_author);
			holder.txt_duration = (TextView)convertView.findViewById(R.id.txt_duration);
			holder.txt_updated = (TextView)convertView.findViewById(R.id.txt_updated);

			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder)convertView.getTag();
		}

		if(entry != null)
		{
			holder.txt_title.setText(entry.getTitle());
			holder.txt_author.setText(entry.getAuthor().getName());
			holder.txt_duration.setText(String.valueOf(entry.getGroup().getDuration()));
			holder.txt_updated.setText(entry.getUpdated());
			if(ImageCache.getImage(entry.getId()) == null)
			{
				if(entry.getGroup() != null && entry.getGroup().getThumbnail() != null)
				{
					if(entry.getGroup().getThumbnail().size() > 0 && holder.task == null)
					{
						holder.task = new ThumbnailTask(holder.image);
						holder.task.execute(entry.getGroup().getThumbnail().get(0).getUrl(), entry.getId());
					}
					else
					{
						holder.image.setImageBitmap(null);

						//受信処理は完了しているが、受信が失敗した場合
						if(holder.task.getStatus() == AsyncTask.Status.FINISHED)
						{
							//再度リクエストする
							holder.task = new ThumbnailTask(holder.image);
							holder.task.execute(entry.getGroup().getThumbnail().get(0).getUrl(), entry.getId());
						}
					}
				}
			}
			else
			{
				holder.image.setImageBitmap(ImageCache.getImage(entry.getId()));
			}
		}
		return convertView;
	}

	class ViewHolder
	{
		ImageView image;
		TextView txt_title;
		TextView txt_author;
		TextView txt_updated;
		TextView txt_duration;
		ThumbnailTask task;
	}
}
