package com.intel.android.logservice;

import android.os.RemoteException;

import com.intel.android.logcommon.ILogService;
import com.intel.android.loglib.LogLib;

public class ILogServiceImpl extends ILogService.Stub {

	@Override
	public void log(int priority, String tag, String message) throws RemoteException {
		LogLib.log(priority, tag, message);
	}
}
