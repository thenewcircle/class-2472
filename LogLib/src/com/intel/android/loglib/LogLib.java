package com.intel.android.loglib;

public class LogLib {
	
    public static native void log(int priority, String tag, String message);
    
    static {
    	System.loadLibrary("intellog");
    }
}
