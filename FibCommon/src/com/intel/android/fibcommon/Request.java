package com.intel.android.fibcommon;

import android.os.Parcel;
import android.os.Parcelable;

public class Request implements Parcelable {
	private int algorithm;
	private long n;

	public Request(int algorithm, long n) {
		super();
		this.algorithm = algorithm;
		this.n = n;
	}

	/** Creates object from parcel. */
	public Request(Parcel source) {
		this(source.readInt(), source.readLong());
	}

	/** Writes object to parcel. */
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(algorithm);
		dest.writeLong(n);
	}

	// --- Required for Parcelable

	public static final Parcelable.Creator<Request> CREATOR = new Parcelable.Creator<Request>() {

		@Override
		public Request createFromParcel(Parcel source) {
			return new Request(source);
		}

		@Override
		public Request[] newArray(int size) {
			return new Request[size];
		}

	};

	@Override
	public int describeContents() {
		return 0;
	}

	// --- Getters and Setters ---
	public int getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(int algorithm) {
		this.algorithm = algorithm;
	}

	public long getN() {
		return n;
	}

	public void setN(long n) {
		this.n = n;
	}
}
