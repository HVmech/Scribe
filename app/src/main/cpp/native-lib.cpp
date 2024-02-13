#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_example_test_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

JNICALL
extern "C"
JNIEXPORT jfloat JNICALL
Java_com_example_test_MyView_getX(JNIEnv *env, jobject thiz, jfloat x, jfloat y) {
    return x + y;
}