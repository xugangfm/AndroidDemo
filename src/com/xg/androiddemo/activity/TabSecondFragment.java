package com.xg.androiddemo.activity;

import com.xg.androiddemo.R;

import android.support.v4.app.Fragment;
//import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
		
		return v;
	}

}
