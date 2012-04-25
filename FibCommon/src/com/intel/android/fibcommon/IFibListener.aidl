package com.intel.android.fibcommon;

import com.intel.android.fibcommon.Response;

oneway interface IFibListener {
	void onResponse(in Response response);
}