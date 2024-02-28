#include <jni.h>
#include <string>

constexpr float v_loss = 0.2;
constexpr float v_min = 1;
constexpr float g = 1;

struct borders {
    float height = 0;
    // float width = 0;
};

borders window;

struct vect {
    float lenght() {
        return sqrt(x * x + y * y);
    }

    void normal() {
        x = x / this->lenght();
        y = y / this->lenght();
    }

    float dot(vect& v) {
        return x * v.x + y * v.y;
    }

    void reflection(vect n) {
        //n.normal();
        x = - 2 * n.x * this->dot(n) / n.dot(n) + x;
        y = - 2 * n.y * this->dot(n) / n.dot(n) + y;
    }

    void collision(float loss_percent, float min_req) {
        x = x * (1 - loss_percent) > min_req ? x * (1 - loss_percent) : 0;
        y = y * (1 - loss_percent) > min_req ? y * (1 - loss_percent) : 0;
    }

    float x, y;
};

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
Java_com_example_test_MyView_setHeight(JNIEnv *env, jobject thiz, jfloat h) {
    window.height = h;
}

extern "C"
JNIEXPORT jobject JNICALL
Java_com_example_test_MyView_calculateNewConditionOfBall(JNIEnv *env, jobject thiz, jobject ball) {
    jclass cpp_ball = env->GetObjectClass(ball);
    jfieldID y = env->GetFieldID(cpp_ball, "y", "F");
    jfieldID vy = env->GetFieldID(cpp_ball, "Vy", "F");
    jfieldID vx = env->GetFieldID(cpp_ball, "Vx", "F");
    jfieldID size = env->GetFieldID(cpp_ball, "ballSize", "I");

    if(env->GetFloatField(ball, y) > window.height - env->GetIntField(ball, size) && env->GetFloatField(ball, vy) > 0 || env->GetFloatField(ball, y) < 0 && env->GetFloatField(ball, vy) < 0) {
        vect t {env->GetFloatField(ball, vx), env->GetFloatField(ball, vy)};
        t.collision(v_loss, v_min);
        t.reflection({0, window.height});
        env->SetFloatField(ball, vy, t.y);
    }
    if(env->GetFloatField(ball, vy) < 0 || env->GetFloatField(ball, y) < window.height - env->GetIntField(ball, size)) {
        env->SetFloatField(ball, vy, env->GetFloatField(ball, vy) + g/2);
    }
    env->SetFloatField(ball, y, env->GetFloatField(ball, y) + env->GetFloatField(ball, vy));
    return ball;
}

/* TODO: Delete
extern "C"
JNIEXPORT jint JNICALL
Java_com_example_test_MyView_getY(JNIEnv *env, jobject thiz, jint size_circul) {
    return int(window.height - size_circul);
}
*/