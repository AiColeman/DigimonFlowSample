package com.github.aicoleman.digimon_flow_sample.data.remote

import okhttp3.Interceptor
import okhttp3.Response

class HTTPInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val request = originalRequest
            .newBuilder()
//            .addHeader(HOST_NAME, HOST_VALUE)
//            .addHeader(KEY_NAME, KEY_VALUE)
            .build()

        return chain.proceed(request)
    }

    companion object {
        const val HOST_NAME = ""
        const val HOST_VALUE = ""
        const val KEY_NAME = ""
        const val KEY_VALUE = ""
    }
}