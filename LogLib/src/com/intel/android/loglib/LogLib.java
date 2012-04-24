package com.intel.android.loglib;

public class LogLib {
	public static final int VERBOSE = 2;
	public static final int DEBUG = 3;
	public static final int INFO = 4;
	public static final int WARNING = 5;
	public static final int ERROR = 6;
	
    public static native void log(int priority, String tag, String message);
    
    static {
    	System.loadLibrary("intellog");
    }
}
