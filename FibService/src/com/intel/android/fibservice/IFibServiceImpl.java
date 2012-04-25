package com.intel.android.fibservice;

import android.os.RemoteException;

import com.intel.android.fibcommon.IFibListener;
import com.intel.android.fibcommon.IFibService;
import com.intel.android.fibcommon.Request;
import com.intel.android.fibcommon.Response;

public class IFibServiceImpl extends IFibService.Stub {

	@Override
	public long fibJ(long n) throws RemoteException {
		return FibLib.fibJ(n);
	}

	@Override
	public long fibJI(long n) throws RemoteException {
		return FibLib.fibJI(n);
	}

	@Override
	public long fibN(long n) throws RemoteException {
		return FibLib.fibN(n);
	}

	@Override
	public long fibNI(long n) throws RemoteException {
		return FibLib.fibNI(n);
	}

	@Override
	public Response fib(Request request) throws RemoteException {
		long result = -1;
		long n = request.getN();
		long start = System.currentTimeMillis();

		switch (request.getAlgorithm()) {
		case Request.ALGORITHM_JAVA_REQURSIVE:
			result = FibLib.fibJ(n);
		case Request.ALGORITHM_JAVA_ITERATIVE:
			result = FibLib.fibJI(n);
		case Request.ALGORITHM_NATIVE_REQURSIVE:
			result = FibLib.fibN(n);
		case Request.ALGORITHM_NATIVE_ITERATIVE:
			result = FibLib.fibNI(n);
		}

		long time = System.currentTimeMillis() - start;

		return new Response(result, time);
	}

	@Override
	public void asyncFib(Request request, IFibListener listener)
			throws RemoteException {
		// Could take a while...
		Response response = fib(request);
		
		// Call back the client when done
		listener.onResponse(response);
	}

}
