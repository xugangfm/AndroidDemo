package com.xg.androiddemo.adapter;

import java.util.HashMap;
import java.util.List;

import com.xg.androiddemo.R;
import com.xg.androiddemo.event.SubMenuOnClickListener;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

// interface myButtonOnClickListener {
//	public void buttonOfListViewClicked(int index,View v);
//}

public class CustomListviewAdapter extends BaseAdapter {
	private Context context = null;
	private LayoutInflater layoutInflater = null;
	private List<HashMap<String, Object>> data_list = null;
	
	SubMenuOnClickListener invoker;

	public CustomListviewAdapter(Context context,
			SubMenuOnClickListener invoker,
			List<HashMap<String, Object>> data_list) {
		this.context = context;
		this.data_list = data_list;
		this.invoker=invoker;
		// this.layoutInflater=LayoutInflater.from(this.context);
		this.layoutInflater = ((LayoutInflater) this.context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.data_list != null ? this.data_list.size() : 0;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.data_list.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		if (convertView == null) {
			convertView = this.layoutInflater.inflate(R.layout.list_row_item,
					null);
		}

		TextView title = (TextView) convertView
				.findViewById(R.id.list_item_title_text);
		TextView detail = (TextView) convertView
				.findViewById(R.id.list_item_detail_text);
		ImageView imageView = (ImageView) convertView
				.findViewById(R.id.list_item_image_view);

		LinearLayout button_menu = (LinearLayout) convertView
				.findViewById(R.id.list_view_sub_menu);
		Button button1 = (Button) convertView
				.findViewById(R.id.list_item_buttun1);

		button1.setOnClickListener(new btClickListener(position));
		// button1.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// // TODO Auto-generated method stub
		//
		// Log.i("click", "ok");
		//
		// }
		// });

		title.setText(this.data_list.get(position).get("title").toString());
		detail.setText(this.data_list.get(position).get("detail").toString());

		//Integer image_id = (Integer) this.data_list.get(position).get("image");

		imageView.setImageResource(R.drawable.book_icon);

		Boolean flag = (Boolean) this.data_list.get(position).get("open_flag");

		if (flag) {
			button_menu.setVisibility(View.VISIBLE);
		} else {
			button_menu.setVisibility(View.GONE);
		}

		return convertView;
	}

	class btClickListener implements OnClickListener {
		private int index;

		public btClickListener(int index) {
			super();
			this.index = index;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			Log.v("click", "ok" + "  " + this.index);
			invoker.viewClicked(v, index);

		}
	}

}
