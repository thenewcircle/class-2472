package com.intel.android.loglib;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class LogLibActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        LogLib.log(3, "LogLib", "onCreated");
    }
    
    public void onClick(View v) {
        LogLib.log(3, "LogLib", "onCreated");
    }
}