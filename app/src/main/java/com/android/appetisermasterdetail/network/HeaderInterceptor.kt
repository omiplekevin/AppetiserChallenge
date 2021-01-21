package com.android.appetisermasterdetail.network

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Accept-Language", "en")
                .addHeader("Content-Type", "application/json")
                .build()
        )
    }
}