package com.intel.android.fibcommon;

import com.intel.android.fibcommon.Request;
import com.intel.android.fibcommon.Response;
import com.intel.android.fibcommon.IFibListener;

interface IFibService {
	long fibJ(long n);
	long fibJI(long n);
	long fibN(long n);
	long fibNI(long n);
	
	Response fib(in Request r);
	
	oneway void asyncFib(in Request r, in IFibListener listener);
}