#include <jni.h>
#include <string>


extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_tiptime_DB_RetrofitInstance_getBaseUrlFromNative(JNIEnv *env, jobject thiz) {

//    converting Url to java and returning the Url
    std::string baseUrl = "https://newsapi.org";
    return env->NewStringUTF(baseUrl.c_str());

}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_tiptime_DB_RetrofitInstance_getApiFromNative(JNIEnv *env, jobject thiz) {

    std::string apiKey = "b3075ce86ddd47b2866543f66c7bc382";
    return env->NewStringUTF(apiKey.c_str());
}



