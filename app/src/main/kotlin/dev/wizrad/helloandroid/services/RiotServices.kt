package dev.wizrad.helloandroid.services

import com.squareup.okhttp.OkHttpClient
import dev.wizrad.helloandroid.BuildConfig
import dev.wizrad.helloandroid.services.utilities.LoggingInterceptor
import dev.wizrad.helloandroid.services.utilities.RiotServicesInterceptor

import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory

public class RiotServices {

    companion object {

        //
        // region Constants
        //

        public val BASE_URL = "https://na.api.pvp.net"
        public val API_KEY  = BuildConfig.RIOT_API_KEY

        // endregion

        //
        // region Factory Methods
        //

        public fun create<TService>(klass: Class<TService>) : TService  {
            // construct the service builder
            val builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                // inject the appropriate factories
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                // inject a custom client
                .client(this.createClient())
                .build()

            // construct a service instance
            return builder.create(klass)
        }

        // endregion

        //
        // region Helpers
        //

        private fun createClient(): OkHttpClient {
            val client       = OkHttpClient()
            val interceptors = client.networkInterceptors()

            interceptors.add(RiotServicesInterceptor(API_KEY))
            interceptors.add(LoggingInterceptor())

            return client
        }

        // endregion
    }
}