package com.intel.android.logclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.intel.android.logcommon.ILogService;
import com.intel.android.loglib.LogLib;

public class LogClientActivity extends Activity {
	ILogService logService;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bindService(new Intent("com.intel.android.logcommon.ILogService"),
				new ServiceConnection() {
					@Override
					public void onServiceConnected(ComponentName name,
							IBinder binder) {
						logService = ILogService.Stub.asInterface(binder);
					}

					@Override
					public void onServiceDisconnected(ComponentName name) {
						logService = null;
					}
				}, Context.BIND_AUTO_CREATE);
	}

	public void onClick(View v) throws RemoteException {
		logService.log(LogLib.DEBUG, "LogLib", "onCreated");
	}
}