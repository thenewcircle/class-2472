package com.intel.android.fibclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.intel.android.fibcommon.IFibListener;
import com.intel.android.fibcommon.IFibService;
import com.intel.android.fibcommon.Request;
import com.intel.android.fibcommon.Response;

public class FibClientActivity extends Activity {
	public static final String TAG = "FibClientActivity";
	private EditText input;
	private TextView output;
	private IFibService fibService;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Setup UI
		setContentView(R.layout.main);
		input = (EditText) findViewById(R.id.input);
		output = (TextView) findViewById(R.id.output);
		
		Log.d(TAG, "onCreated");
	}
	

	FibServiceConnection conn;
	
	@Override
	protected void onStart() {
		super.onStart();
		conn = new FibServiceConnection();
		// Bind to the service
		boolean isBound = bindService(new Intent("com.intel.android.fibcommon.IFibService"),
				conn, Context.BIND_AUTO_CREATE);
	}

	@Override
	protected void onStop() {
		super.onStop();
		unbindService(conn);
		conn = null;
	}



	class FibServiceConnection implements ServiceConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			fibService = IFibService.Stub.asInterface(binder);
			Log.d(TAG, "onServiceConnected");
		}
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			fibService = null;
			Log.d(TAG, "onServiceDisconnected");
		}

	}

	/** Called when button is pressed. 
	 * @throws RemoteException */
	public void onClick(View v) throws RemoteException {
		long n = Long.parseLong(input.getText().toString());

		Response response;
		Request request = new Request(-1,n);
		
		// Java version
		request.setAlgorithm(Request.ALGORITHM_JAVA_REQURSIVE);
		response = fibService.fib(request);
		output.append("\nJava R: " + response.toString());

		// Native version
		request.setAlgorithm(Request.ALGORITHM_JAVA_ITERATIVE);
		response = fibService.fib(request);
		output.append("\nNative R: " + response.toString());

		// Java iterative version
		request.setAlgorithm(Request.ALGORITHM_NATIVE_REQURSIVE);
		response = fibService.fib(request);
		output.append("\nJava I: " + response.toString());

		// Native iterative version
		request.setAlgorithm(Request.ALGORITHM_NATIVE_ITERATIVE);
		response = fibService.fib(request);
		output.append("\nNative I: " + response.toString());

	}
	
	/** Called to async process Java recursive call. */
	public void onClickAsync(View v) throws RemoteException {
		long n = Long.parseLong(input.getText().toString());
		
		Request request;
		if(v.getId()==R.id.button_java)
			request = new Request(Request.ALGORITHM_JAVA_REQURSIVE, n);
		else 
			request = new Request(Request.ALGORITHM_NATIVE_REQURSIVE, n);
		
		fibService.asyncFib(request, listener);
		this.finish();
	}

	private static int MESSAGE_ID=47;
	// Created on UI thread
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {	
			if(msg.what==MESSAGE_ID) {
				Response response = (Response)msg.obj;
				output.append("\nAsync R: "+response.toString());
			}
		}
		
	};

	/** Called back from the service when the result is ready. */
	private IFibListener listener = new IFibListener.Stub() {		
		@Override
		public void onResponse(Response response) throws RemoteException {
			Log.d("LogClient", response.toString());
			Message msg = handler.obtainMessage(MESSAGE_ID, response);
			handler.sendMessage(msg);
		}
	}; 
}