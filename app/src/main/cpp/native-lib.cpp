#include <jni.h>
#include <string>

struct Hitbox {
    float height = 0;
};

Hitbox window;

extern "C" JNIEXPORT jstring

JNICALL
Java_com_example_test_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C"
JNIEXPORT void JNICALL
// this method get you actual height of view
// And note, that for you was saved method stringFromJNI() for test output! Good luck!
Java_com_example_test_MyView_setHeight(JNIEnv *env, jobject thiz, jfloat h) {
    window.height = h;
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_example_test_MyView_calculateNewConditionOfBall(JNIEnv *env, jobject thiz, jobject ball) {
    jclass CppBall = env->GetObjectClass(ball);
    jfieldID CppBallY = env->GetFieldID(CppBall, "y", "F");
    jfieldID CppBallVy = env->GetFieldID(CppBall, "Vy", "F");
    jfieldID CppBallSize = env->GetFieldID(CppBall, "ballSize", "I");

    if(env->GetFloatField(ball, CppBallY) > window.height - env->GetIntField(ball, CppBallSize) && env->GetFloatField(ball, CppBallVy) > 0) {
            env->SetFloatField(ball, CppBallVy, -(env->GetFloatField(ball, CppBallVy)));
    }
    else {
        if(env->GetFloatField(ball, CppBallY) < 0 && env->GetFloatField(ball, CppBallVy) < 0) {
            env->SetFloatField(ball, CppBallVy, -(env->GetFloatField(ball, CppBallVy)));
        }
    }
    env->SetFloatField(ball, CppBallY, env->GetFloatField(ball, CppBallY) + env->GetFloatField(ball, CppBallVy));
    return ball;
}