package dev.wizrad.helloandroid.services.utilities

import android.util.Log
import com.squareup.okhttp.Interceptor
import com.squareup.okhttp.Request
import com.squareup.okhttp.Response
import com.squareup.okhttp.ResponseBody

class LoggingInterceptor(
    val baseUrl: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain) : Response {
        val request = chain.request()
        val url     = this.relativizeUrlString(request)

        Log.i("Services.Request", url)
        Log.v("Services.Request", "${request.headers()}")

        val response = chain.proceed(request)

        Log.i("Services.Response", url)
        Log.v("Services.Response", "${response.headers()}")

        return response
    }

    private fun relativizeUrlString(request: Request) : String {
        val absolutePath = request.urlString()
        var relativePath = absolutePath.removePrefix(this.baseUrl)

        return relativePath
    }

}