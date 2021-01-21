package com.android.appetisermasterdetail.di.module

import com.android.appetisermasterdetail.BuildConfig
import com.android.appetisermasterdetail.network.AppleAPI
import com.android.appetisermasterdetail.network.HeaderInterceptor
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module
object APIModule {

    /**
     * Provides the AppleAPI service implementation.
     * @param retrofit the Retrofit object used to instantiate the service
     * @return the AppleAPI service implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    @Named("AppleNoAuthAPI")
    internal fun provideNonAuthAppleAPI(@Named("AppleNoAuthInstance") retrofit: Retrofit): AppleAPI =
        retrofit.create(AppleAPI::class.java)

    /**
     * Provides the Retrofit object.
     * @return the Retrofit object
     */
    @Provides
    @Reusable
    @JvmStatic
    @Named("AppleNoAuthInstance")
    internal fun provideNoAuthApple(): Retrofit =
        makeAppleRetrofit()

    /**
     * Creates Retrofit instance with Apple implementation
     */
    private fun makeAppleRetrofit(vararg interceptors: Interceptor): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.APPLE_DOMAIN)
            .client(makeHttpClient(interceptors))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    /**
     * Creates HttpClient with given interceptor(s)
     * @param interceptors optional interceptors for creating HttpClient
     */
    private fun makeHttpClient(interceptors: Array<out Interceptor>): OkHttpClient =
        OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .followRedirects(true)
            .addInterceptor(HeaderInterceptor())
            .addInterceptor(loggingInterceptor())
            .apply { interceptors().addAll(interceptors) }
            .build()

    private fun loggingInterceptor() = HttpLoggingInterceptor().apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

}