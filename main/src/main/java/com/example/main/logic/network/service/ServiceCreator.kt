package com.example.main.logic.network.service

import android.os.Build
import com.example.base.utils.GlobalUtil
import com.example.base.utils.logV
import com.example.base.utils.DensityUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*

/**
 * 请求服务的构建器，用来构建Retrofit对象；其中为OkHttpClient增加了几个拦截器
 */
object ServiceCreator {

    const val BASE_URL = "http://baobab.kaiyanapp.com/"

    private val customClient: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(BaseInterceptor())
        .addInterceptor(HeaderInterceptor())
        .addInterceptor(BasicParamsInterceptor())
        .build()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(customClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    //这里用了一波内联函数的泛型实化
    inline fun <reified T> create(): T = retrofit.create(T::class.java)

    //基本拦截器，其中打印了请求和相应的一些基本信息的日志
    class BaseInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val sendTime = System.nanoTime()
            logV(TAG, "请求基本信息\nurl：${request.url()}，headers：${request.headers()}")
            val response = chain.proceed(request)
            val receivedTime = System.nanoTime()
            logV(TAG, "响应基本信息\n请求用时：${(receivedTime - sendTime) / 1000}毫秒，响应状态码：${response.code()}")
            return response
        }

        companion object {
            const val TAG = "BaseInterceptor"
        }
    }

    //请求头拦截器，在基本的请求头中增加了几个字段
    class HeaderInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original = chain.request()
            val request = original.newBuilder().apply {
                header("model", "Android")
                header("If-Modified-Since", "${Date()}")
                header("User-Agent", System.getProperty("http.agent") ?: "unknown")
            }.build()
            return chain.proceed(request)
        }
    }

    //基本参数拦截器，根据我抓到的请求url来看，这些就是开眼请求URL中的一些参数
    class BasicParamsInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url()
            val url = originalHttpUrl.newBuilder().apply {
                addQueryParameter("udid", GlobalUtil.getDeviceSerial())
                addQueryParameter("vc", GlobalUtil.eyepetizerVersionCode.toString())
                addQueryParameter("vn", GlobalUtil.eyepetizerVersionName)
                addQueryParameter("size", DensityUtil.getScreenPx())
                addQueryParameter("deviceModel", GlobalUtil.deviceModel)
                addQueryParameter("first_channel", GlobalUtil.deviceBrand)
                addQueryParameter("last_channel", GlobalUtil.deviceBrand)
                addQueryParameter("system_version_code", "${Build.VERSION.SDK_INT}")
            }.build()
            logV("BaseInterceptor", "完整URL：$url")
            val request = originalRequest.newBuilder().url(url)
                .method(originalRequest.method(), originalRequest.body()).build()
            return chain.proceed(request)
        }
    }
}