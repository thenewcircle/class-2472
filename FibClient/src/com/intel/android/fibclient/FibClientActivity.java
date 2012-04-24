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

		// Java version
		long start = System.currentTimeMillis();
		long resultJ = fibService.fibJ(n);
		long timeJ = System.currentTimeMillis() - start;
		output.append(String.format("\nfibJ(%d)=%d (%d ms)", n, resultJ, timeJ));

		// Native version
		start = System.currentTimeMillis();
		long resultN = fibService.fibN(n);
		long timeN = System.currentTimeMillis() - start;
		output.append(String.format("\nfibN(%d)=%d (%d ms)", n, resultN, timeN));

		// Java iterative version
		start = System.nanoTime();
		long resultJI = fibService.fibJI(n);
		long timeJI = System.nanoTime() - start;
		output.append(String.format("\nfibJI(%d)=%d (%d ns)", n, resultJI,
				timeJI));

		// Native iterative version
		start = System.nanoTime();
		long resultNI = fibService.fibNI(n);
		long timeNI = System.nanoTime() - start;
		output.append(String.format("\nfibNI(%d)=%d (%d ns)", n, resultNI,
				timeNI));

	}
}