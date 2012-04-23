package com.intel.android.fib;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FibNativeActivity extends Activity {
	EditText input;
	TextView output;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        input = (EditText) findViewById(R.id.input);
        output = (TextView)findViewById(R.id.output);
    }
    
    /** Called when button is pressed. */
    public void onClick(View v) {
    		long n = Long.parseLong( input.getText().toString() );
    		
    		// Java version
    		long start = System.currentTimeMillis();
    		long resultJ = FibLib.fibJ(n);
    		long timeJ = System.currentTimeMillis() - start;
    		output.append( String.format("\nfibJ(%d)=%d (%d ms)", n, resultJ, timeJ) );
    		
    		// Native version
    		start = System.currentTimeMillis();
    		long resultN = FibLib.fibN(n);
    		long timeN = System.currentTimeMillis() - start;
    		output.append( String.format("\nfibN(%d)=%d (%d ms)", n, resultN, timeN) );
    }
}