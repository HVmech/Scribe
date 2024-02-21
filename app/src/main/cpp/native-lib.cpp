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
JNIEXPORT jobject JNICALL
Java_com_example_test_MyView_calculateNewConditionOfBall(JNIEnv *env, jobject thiz, jobject ball) {
    jclass CppBall = env->GetObjectClass(ball);
    jfieldID CppBallX = env->GetFieldID(CppBall, "x", "F");

    env->SetFloatField(ball, CppBallX, env->GetFloatField(ball, CppBallX) + 2.0f);
    return ball;
}