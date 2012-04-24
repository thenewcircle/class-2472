#include "com_intel_android_loglib_LogLib.h"
#include <android/log.h>

JNIEXPORT void JNICALL Java_com_intel_android_loglib_LogLib_log
(JNIEnv *env, jclass clazz, jint priority, jstring tag_str, jstring message_str) {

	// Create C string
	const char *tag = (*env)->GetStringUTFChars(env, tag_str, 0);
	const char *message = (*env)->GetStringUTFChars(env, message_str, 0);

	__android_log_write(priority, tag, message);

	// Release C string
	(*env)->ReleaseStringUTFChars(env, tag_str, tag);
	(*env)->ReleaseStringUTFChars(env, message_str, message);
}
