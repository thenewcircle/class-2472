package com.intel.android.logclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.intel.android.logcommon.Message;

public class LogClientActivity extends Activity {
	LogManager logManager;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		logManager = new LogManager(this);
	}

	public void onClick(View v) {
		// logManager.log(Message.DEBUG, "LogLib", "onCreated");
		logManager
				.logMessage(new Message(Message.DEBUG, "LogLib", "onCreated"));
	}
}