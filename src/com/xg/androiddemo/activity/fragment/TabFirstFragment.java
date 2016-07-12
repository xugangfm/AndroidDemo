package com.xg.androiddemo.activity.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.xg.androiddemo.R;

import java.util.ArrayList;


public class TabFirstFragment extends Fragment {

	private Button button1;
	private Button button2;

    private  TabFirstFragmentClickListener mListener;


    public interface TabFirstFragmentClickListener{
        public  void onclicked (int resId,int index);
    }


    @Override
    public void onAttach(Activity activity){

        super.onAttach(activity);
        try{

            mListener =(TabFirstFragmentClickListener)activity;

        }catch(ClassCastException e){
            throw new ClassCastException(activity.toString()+"must implement OnArticleSelectedListener");
        }
    }

    @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);


		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//return super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.activity_tab_first_layout, container, false);


		button1 = (Button) v.findViewById(R.id.first_act_button1);

		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                mListener.onclicked(R.id.first_act_button1,0);

			}
		});

		button2 = (Button) v.findViewById(R.id.first_act_button2);

		button2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                mListener.onclicked(R.id.first_act_button2,0);

			}
		});


        ListView lv=(ListView) v.findViewById(R.id.first_act_listview);

        ArrayList<String> arrayList= new ArrayList<String>();

        arrayList.add("Download image activity");
        arrayList.add("try service activity");
        arrayList.add("post notification");
        arrayList.add("try broardcast activity");
        arrayList.add("try handle with thread(eventbus2.4)");
        arrayList.add("Test Tab ActionBar Activity");
        arrayList.add("Test List ActionBar Activity");
        arrayList.add("Test DB Activity");
        arrayList.add("Test  Activity launch mode");




        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, arrayList);

        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub

                int index = arg2;
                Log.v("list demo index", index + "");
                if (index == 0) {
                   mListener.onclicked(R.id.first_act_listview,index);
                }
                if (index == 1) {
                    mListener.onclicked(R.id.first_act_listview,index);
                }

                if (index == 2) {
                    //postNotification();
                    mListener.onclicked(R.id.first_act_listview,index);
                }

                if (index == 3) {
                    mListener.onclicked(R.id.first_act_listview,index);
                }

                if (index == 4) {
                    mListener.onclicked(R.id.first_act_listview,index);
                }

                if (index == 5) {
                    mListener.onclicked(R.id.first_act_listview,index);
                }

                if (index == 6) {
                    mListener.onclicked(R.id.first_act_listview,index);
                }
                if (index == 7) {
                    mListener.onclicked(R.id.first_act_listview,index);
                }
                if (index == 8) {
                    mListener.onclicked(R.id.first_act_listview,index);
                }


            }
        });

		return v;
	}

	
}
