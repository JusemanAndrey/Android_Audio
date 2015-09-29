package com.audio.main;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter {

	private ArrayList<NewsItem> listData;

	private LayoutInflater layoutInflater;

	public CustomListAdapter(Context context, ArrayList<NewsItem> listData) {
		this.listData = listData;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.check_list_detail, null);
			holder = new ViewHolder();
			
			holder.play_but_info = (Button) convertView.findViewById(R.id.play_but_info);
			holder.pause_but_info = (Button)convertView.findViewById(R.id.media_pause_info);
			holder.second_show = (TextView) convertView.findViewById(R.id.second);
			holder.i_title = (TextView) convertView.findViewById(R.id.i_title);
			holder.e_title = (TextView) convertView.findViewById(R.id.e_title);
			holder.number_but = (Button) convertView.findViewById(R.id.number_but);
			holder.layout = (LinearLayout) convertView.findViewById(R.id.main_layout);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.second_show.setText(listData.get(position).getSecond());
		holder.i_title.setText(listData.get(position).getITitle());
		holder.e_title.setText(listData.get(position).getETitle());
		holder.number_but.setText(listData.get(position).getNumber());
		holder.number_but.setBackgroundResource(listData.get(position).getImageId());
		
		holder.play_but_info.setVisibility(listData.get(position).getVisible() ? View.VISIBLE : View.GONE);				
		holder.pause_but_info.setVisibility(listData.get(position).getPauseVisible() ? View.VISIBLE : View.GONE);
		holder.layout.setBackgroundResource(listData.get(position).getBgImageId());
		
		return convertView;
	}

	static class ViewHolder {
		Button play_but_info, pause_but_info, number_but;
		TextView second_show;
		TextView i_title;
		TextView e_title;
		LinearLayout layout;
	}

}
