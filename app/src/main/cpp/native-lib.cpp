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

extern "C"
JNIEXPORT jfloat JNICALL
Java_com_example_test_MyView_calculatePointXOfBall(JNIEnv *env, jobject thiz, jfloat y) {
    // TODO: implement calculatePointXOfBall()
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_example_test_MyView_calculateNewConditionOfBall(JNIEnv *env, jobject thiz, jobject ball) {
    // TODO: implement calculateNewConditionOfBall()
}