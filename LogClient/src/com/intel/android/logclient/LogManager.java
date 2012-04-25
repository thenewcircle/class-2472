package com.intel.android.logclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.intel.android.logcommon.*;

public class LogManager {
	private ILogService logService;

	public LogManager(Context context) {
		context.bindService(new Intent(
				"com.intel.android.logcommon.ILogService"),
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

	// --- Proxy calls to the remote service

	public void log(int priority, String tag, String message) {
		try {
			logService.log(priority, tag, message);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void logMessage(Message message) {
		try {
			logService.logMessage(message);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
