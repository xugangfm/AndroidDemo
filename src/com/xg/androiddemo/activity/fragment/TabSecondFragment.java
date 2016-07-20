package com.xg.androiddemo.activity.fragment;

import com.xg.androiddemo.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.net.URI;
import java.util.ArrayList;

public class TabSecondFragment extends Fragment {

	private  ConversationType mConversationType;

	public  enum  ConversationType{

		PRIVATE(1,"private"),

		GROUP(2,"group");

		private int v = 0;
		private String s = "";


		ConversationType(int v,String s){
			this.s=s;
			this.v=v;
		}
		public int getValue(){return v;}
		public String getName(){return s;}

		public static ConversationType setValue(int code) {
			for (ConversationType c : ConversationType.values()) {
				if (code == c.getValue()) {
					return c;
				}
			}
			return PRIVATE;
		}

		@Override
		public String toString(){

			return s;
		}

	}


	private Activity mActivity;

	@Override
	public  void onCreate(Bundle b){
		super.onCreate(b);

		mConversationType = ConversationType.GROUP;

		Log.i(mConversationType.getName(),""+mConversationType.getValue());

		mConversationType = ConversationType.setValue(1);

		Log.i(mConversationType.getName(),""+mConversationType.getValue());

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.activity_tab_second_layout, container, false);

		ListView lv=(ListView) v.findViewById(R.id.second_act_listview);
		ArrayList<String> arrayList= new ArrayList<String>();

		arrayList.add("URI Test open web");
        arrayList.add("URI Test dail ");




		ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);

		lv.setAdapter(adapter);

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
				// TODO Auto-generated method stub

				int index = arg2;
				Log.v("list demo index", index + "");

                if(0 ==index){
                    Uri uri = Uri.parse("http://www.baidu.com");
                    Intent it =new Intent(Intent.ACTION_VIEW,uri);

                    startActivity(it);

                }else if(1==index){
                    Uri uri = Uri.parse("tel:10086");
                    Intent it =new Intent(Intent.ACTION_DIAL,uri);

                    startActivity(it);
                }


			}
		});
		return v;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mActivity = activity;
	}
}
