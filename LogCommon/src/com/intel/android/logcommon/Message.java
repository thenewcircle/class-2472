package com.intel.android.logcommon;

import android.os.Parcel;
import android.os.Parcelable;

public class Message implements Parcelable {
	public static final int VERBOSE = 2;
	public static final int DEBUG = 3;
	public static final int INFO = 4;
	public static final int WARNING = 5;
	public static final int ERROR = 6;

	private int priority;
	private String tag;
	private String text;

	public Message(int priority, String tag, String text) {
		super();
		this.priority = priority;
		this.tag = tag;
		this.text = text;
	}

	@Override
	public String toString() {
		return String.format("%d/%s: %s", priority, tag, text);
	}

	// --- Parcelable methods ---

	public Message(Parcel source) {
		this(source.readInt(), source.readString(), source.readString());
	}


	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(priority);
		dest.writeString(tag);
		dest.writeString(text);
	}

	public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() {

		@Override
		public Message createFromParcel(Parcel source) {
			return new Message(source);
		}

		@Override
		public Message[] newArray(int size) {
			return new Message[size];
		}
	};
	
	@Override
	public int describeContents() {
		return 0;
	}

	// --- Setters and Getters
	
	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
