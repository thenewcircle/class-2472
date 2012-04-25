package com.intel.android.logservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.intel.android.logcommon.*;

public class LogService extends Service {

	@Override
	public IBinder onBind(Intent intent) {
		return new ILogServiceImpl();
	}

}
