package dev.wizrad.helloandroid.services

import android.util.Log
import com.squareup.okhttp.Interceptor
import com.squareup.okhttp.Response

public class LoggingInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain) : Response {
        val request = chain.request()
        Log.d("Services.Request", request.toString())

        val response = chain.proceed(request)
        Log.v("Services.Response", response.toString())

        return response
    }

}