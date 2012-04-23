package com.intel.android.fib;

public class FibLib {

	/** Java version of Fibonacci algorithm.
	 * @param n Input value
	 * @return Fibonacci of n
	 */
	public static long fibJ(long n) {
		if(n==0) return 0;
		if(n==1) return 1;
		return fibJ(n-1)+fibJ(n-2);
	}
	
	/** Declaration of native Fibonacci function. */
	public static native long fibN(long n);
}
