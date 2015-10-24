package dev.wizrad.helloandroid.services.utilities

import com.squareup.okhttp.Interceptor
import com.squareup.okhttp.Response

class RiotServicesInterceptor(
    val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain) : Response {
        // rebuild the url with the api key query parameter
        val url = chain.request().httpUrl().newBuilder()
            .setQueryParameter("api_key", this.apiKey)
            .build()

        // add the api key to every request
        val request = chain.request().newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }
}