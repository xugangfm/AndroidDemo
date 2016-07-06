package com.xg.androiddemo.activity.test;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.xg.androiddemo.event.ImageDownloadEvent;
import com.xg.androiddemo.parent.BaseActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import de.greenrobot.event.EventBus;

public class HandleTestActivity extends BaseActivity implements Handler.Callback {

	public HandleTestActivity() {
		// TODO Auto-generated constructor stub
	}

    private ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    private ExecutorService executorService = Executors.newCachedThreadPool();
    private Handler mHandler;

	private Handler handler=new Handler(){

		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			
			Bundle data=msg.getData();
			
			Bitmap bitmap =data.getParcelable("bitmap");
			
			ImageView imageView=new ImageView(HandleTestActivity.this);
			imageView.setImageBitmap(bitmap);
			
			new AlertDialog.Builder(HandleTestActivity.this)
			.setTitle("image")
			.setView(imageView)
			.setPositiveButton("OK", null)
			.show();
			
		
		}
		
		
	};

    @Override
    public boolean handleMessage(Message message) {
        Bundle data=message.getData();

        Bitmap bitmap =data.getParcelable("bitmap");

        ImageView imageView=new ImageView(HandleTestActivity.this);
        imageView.setImageBitmap(bitmap);

        new AlertDialog.Builder(HandleTestActivity.this)
                .setTitle("image")
                .setView(imageView)
                .setPositiveButton("OK", null)
                .show();

        return false;
    }

    /*
         * (non-Javadoc)
         *
         * @see com.xg.androiddemo.parent.BaseActivity#onCreate(android.os.Bundle)
         */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
        mHandler = new Handler(this);

        EventBus.getDefault().register(this);

        //eventBus.register(this);

		ListView lv = new ListView(this);

		ArrayList<String> arrayList = new ArrayList<String>();

		arrayList.add("Download image with thread");
		arrayList.add("Download image with runnable");
		
		arrayList.add("handle post runnable");

		arrayList.add("eventbus");

		arrayList.add("线程池ThreadPoolExecutor");

        arrayList.add("okHttp");

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, arrayList);

		lv.setAdapter(adapter);
		
		setContentView(lv);
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			/* (non-Javadoc)
			 * @see android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget.AdapterView, android.view.View, int, long)
			 */
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				
				int index=arg2;
				
				
				if (index == 0) {
					downloadByThread();
				}
				
				if(index ==1){
					downloadByRunnable();
				}


				
				if (index==2) {
                    Log.d("click post", "run:pid "+Thread.currentThread().getId());
					
					handler.post(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
                            Log.d("act post", "run:pid "+Thread.currentThread().getId());
							new AlertDialog.Builder(HandleTestActivity.this)
							.setTitle("handle post")
							.setMessage("post runnable")
							.setPositiveButton("ok", null)
							.setNegativeButton("NO", null)
							.show();
						}
					});
					
				}

                if(index == 3){
                    downloadByThread_eventbus();
                }

                if(index == 4){
                    threadPoolExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            String urlString="http://img5.hao123.com/data/1_d1be20c5f759160fc48b3395d9579edc_0";
                            //Bitmap bitmap=getBitmapFromUrl(urlString);
                            Bitmap bitmap=getBitmapWithHttpClient(urlString);
                            Message msg=handler.obtainMessage();
                            msg.what=1;
                            Bundle bundle=new Bundle();
                            bundle.putParcelable("bitmap", bitmap);
                            msg.setData(bundle);

                            //handler.post(r)
                            //handler.sendMessage(msg);
                            msg.setTarget(handler);
                            msg.sendToTarget();
                        }
                    });
                }

                if(index == 5){

                    threadPoolExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            String urlString="http://img5.hao123.com/data/1_d1be20c5f759160fc48b3395d9579edc_0";
                            try {
                                get(urlString);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });



                }

                if (index == 6){
                    String url="http://img5.hao123.com/data/1_d1be20c5f759160fc48b3395d9579edc_0";
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    try {
                         client.newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Request request, IOException e) {

                            }

                            @Override
                            public void onResponse(Response response) throws IOException {

                                byte[] b=    response.body().bytes();

                                Bitmap bitmap = BitmapFactory.decodeByteArray(b,0,b.length);

                                ImageDownloadEvent ev= new ImageDownloadEvent();
                                ev.uri = "http://img5.hao123.com/data/1_d1be20c5f759160fc48b3395d9579edc_0";
                                ev.bitmap = bitmap;
                                EventBus.getDefault().post(ev);
                            }
                        });



                    }catch(Exception e) {

                    }finally{

                    }
                }
				
			}
		});

	}
	

	
	private void downloadByThread(){
		
		new Thread(){

			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				String urlString="http://img5.hao123.com/data/1_d1be20c5f759160fc48b3395d9579edc_0";				
				//Bitmap bitmap=getBitmapFromUrl(urlString);
				Bitmap bitmap=getBitmapWithHttpClient(urlString);
				Message msg=mHandler.obtainMessage();
				Bundle bundle=new Bundle();
				bundle.putParcelable("bitmap", bitmap);
				msg.setData(bundle);
				
				//handler.post(r)
                mHandler.sendMessage(msg);
				
			}
			
		}.start();
	}
    private void downloadByRunnable(){
        Runnable runable=new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                String urlString="http://img5.hao123.com/data/1_d1be20c5f759160fc48b3395d9579edc_0";
                //Bitmap bitmap=getBitmapFromUrl(urlString);
                Bitmap bitmap=getBitmapWithHttpClient(urlString);
                Message msg=handler.obtainMessage();
                msg.what=1;
                Bundle bundle=new Bundle();
                bundle.putParcelable("bitmap", bitmap);
                msg.setData(bundle);

                //handler.post(r)
                //handler.sendMessage(msg);
                msg.setTarget(handler);
                msg.sendToTarget();

            }
        };

        new Thread(runable).start();
    }
	private  Bitmap getBitmapFromUrl(String imgUrl) {
		URL myFileUrl = null;
		Bitmap bitmap = null;
		try {
			myFileUrl = new URL(imgUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		try {
			HttpURLConnection conn = (HttpURLConnection) myFileUrl
					.openConnection();
			// conn.setDoInput(true);
			conn.connect();
			InputStream is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bitmap;
	}
	
	private Bitmap getBitmapWithHttpClient(String imgUri){
		Bitmap bitmap = null;
		
		try {
			URL url=new URL(imgUri);
			
			URI uri=url.toURI();
			HttpClient client=new DefaultHttpClient();
			
			HttpGet get=new HttpGet(uri);
			
			HttpResponse response=client.execute(get);
			InputStream is =response.getEntity().getContent();
			bitmap = BitmapFactory.decodeStream(is);
			is.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return bitmap;
	}

    private void downloadByThread_eventbus(){

        new Thread(){

            /* (non-Javadoc)
             * @see java.lang.Thread#run()
             */
            @Override
            public void run() {
                // TODO Auto-generated method stub

                String urlString="http://img5.hao123.com/data/1_d1be20c5f759160fc48b3395d9579edc_0";
                //Bitmap bitmap=getBitmapFromUrl(urlString);
                Bitmap bitmap=getBitmapWithHttpClient(urlString);
                ImageDownloadEvent ev= new ImageDownloadEvent();
                ev.uri = urlString;
				ev.bitmap = bitmap;
                EventBus.getDefault().post(ev);

                Log.d("postevent", "run:pid "+Thread.currentThread().getId());
//                Message msg=mHandler.obtainMessage();
//                Bundle bundle=new Bundle();
//                bundle.putParcelable("bitmap", bitmap);
//                msg.setData(bundle);
//
//                //handler.post(r)
//                mHandler.sendMessage(msg);

            }

        }.start();
    }

   // @Subscribe
    public void onEvent(ImageDownloadEvent event){

        Log.e("onEvent", "onEvent: "+event.uri);
        Log.d("onevent", "run: pid "+Thread.currentThread().getId());

//		handler.post(new Runnable() {
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				Log.d("act post", "run:pid "+Thread.currentThread().getId());
//				new AlertDialog.Builder(HandleTestActivity.this)
//						.setTitle("handle post")
//						.setMessage("post onEvent")
//						.setPositiveButton("ok", null)
//						.setNegativeButton("NO", null)
//						.show();
//				Log.d("handlerpostonevent", "run: pid "+Thread.currentThread().getId());
//			}
//		});

		Message msg=mHandler.obtainMessage();
		Bundle bundle=new Bundle();
		bundle.putParcelable("bitmap", event.bitmap);
		msg.setData(bundle);

		//handler.post(r)
		mHandler.sendMessage(msg);


    }

   // @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessage(ImageDownloadEvent event){

        Log.e("onMessage", "onEvent: "+event.uri);
        Log.d("onMessage", "run: pid"+Thread.currentThread().getId());

        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("onMessage run ui thread", "run: pid "+Thread.currentThread().getId());
            }
        });



    }
   // @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(ImageDownloadEvent event){

        Log.e("onEventMainThread", "onEvent: "+event.uri);
        Log.d("onEventMainThread", "run: pid "+Thread.currentThread().getId());


    }

    public void get(String url)throws Exception{

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {

                byte[] b=    response.body().bytes();

                Bitmap bitmap = BitmapFactory.decodeByteArray(b,0,b.length);

                ImageDownloadEvent ev= new ImageDownloadEvent();
                ev.uri = url;
                ev.bitmap = bitmap;
                EventBus.getDefault().post(ev);
            }


        }catch(IOException e) {
            throw new Exception(e);
        }finally{

        }



    }

   // @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEventXg(ImageDownloadEvent event){
//
//        Log.e("onEventXg", "onEvent: "+event.uri);
//        Log.d("onEventXg", "run: pid "+Thread.currentThread().getId());
//
//
//    }
}
