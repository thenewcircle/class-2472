package com.intel.android.logservice;

import android.os.RemoteException;
import com.intel.android.loglib.LogLib;
import com.intel.android.logcommon.*;

public class ILogServiceImpl extends ILogService.Stub {

	@Override
	public void log(int priority, String tag, String message)
			throws RemoteException {
		LogLib.log(priority, tag, message);
	}

	@Override
	public void logMessage(Message message) throws RemoteException {
		LogLib.log(message.getPriority(), message.getTag(), message.getText());
	}
}
