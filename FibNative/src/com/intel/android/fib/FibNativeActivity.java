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
    }
    
    /** Called when button is pressed. */
    public void onClick(View v) {
    		long n = Long.parseLong( input.getText().toString() );
    		
    		long resultJ = FibLib.fibJ(n);
    		output.append( String.format("\nfibJ(%d)=%d", n, resultJ) );
    		
    		long resultN = FibLib.fibN(n);
    		output.append( String.format("\nfibN(%d)=%d", n, resultN) );
    }
}