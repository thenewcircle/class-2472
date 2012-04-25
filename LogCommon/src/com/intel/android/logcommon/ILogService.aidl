package com.intel.android.logcommon;

import com.intel.android.logcommon.Message;

interface ILogService {
	void log(int priority, String tag, String message);
	void logMessage(in Message msg);
}