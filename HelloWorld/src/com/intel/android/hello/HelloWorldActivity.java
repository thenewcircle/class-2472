package com.intel.android.hello;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class HelloWorldActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Log.d("HelloWorld", "onCreated");
    }
}