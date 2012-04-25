package com.intel.android.fibclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

		// Bind to the service
		boolean isBound = bindService(new Intent("com.intel.android.fibcommon.IFibService"),
				new FibServiceConnection(), Context.BIND_AUTO_CREATE);
		
		Log.d(TAG, "onCreated isBound: "+isBound);
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
}