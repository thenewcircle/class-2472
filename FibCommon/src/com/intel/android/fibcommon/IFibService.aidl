package com.intel.android.fibcommon;

import com.intel.android.fibcommon.Request;
import com.intel.android.fibcommon.Response;

interface IFibService {
	long fibJ(long n);
	long fibJI(long n);
	long fibN(long n);
	long fibNI(long n);
	
	Response fib(in Request r);
}