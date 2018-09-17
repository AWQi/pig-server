package com.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.java_websocket.WebSocketImpl;

import com.task.AsyncTask;


public class WebSocketFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

	}

	//  初始化  websocket
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		this.startWebsocketInstantMsg();

		
		
		// 测试异步任务
//		startAsyncTaskTest();
		

	}
	

	/**
	 * 启动长连接服务
	 */
	public void startWebsocketInstantMsg() {
		WebSocketImpl.DEBUG = false;
		WsServer s;
		s = new WsServer(8887);
		s.start();
	}

	
	
	
	
	
	
	private static class MyAsyncTask extends AsyncTask<String, Integer, String> {

		private String mName = "AsyncTask";

		public MyAsyncTask(String name) {
			super();
			mName = name;
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("execute start at " + df.format(new Date()));
		}

		@Override
		protected String doInBackground(String... params) {
//			try {
//
//				for (int i = 0; i < 10; i++) {
//					Thread.sleep(10000);
//					publishProgress(i*10);
//				}
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			return mName;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(result + "execute finish at " + df.format(new Date()));
			ClientManager.getInstance().sendMessageToAll(result + "execute finish at " + df.format(new Date()));
			// Log.e(TAG, result + "execute finish at " + df.format(new Date()));
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			System.out.println("onProgressUpdate" + values[0]);
			super.onProgressUpdate(values);
		}

	}
	
	public void startAsyncTaskTest() {
		new MyAsyncTask("AsyncTask#1").execute("");
	
	}

}
