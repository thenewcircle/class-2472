package com.intel.android.fibservice;

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
	
	/** Java iterative version of Fibonacci. */
    public static long fibJI(long n) {
        long previous = -1;
        long result = 1;
        for (long i = 0; i <= n; i++) {
            long sum = result + previous;
            previous = result;
            result = sum;
        }
        return result;
    }

	
	static {
		System.loadLibrary("fib");
	}
	
	/** Declaration of native Fibonacci function. */
	public static native long fibN(long n);
	
	/** Native interative version of Fibonacci. */
	public static native long fibNI(long n);
}
