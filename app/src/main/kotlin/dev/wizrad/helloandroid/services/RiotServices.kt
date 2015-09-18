package dev.wizrad.helloandroid.services

import dev.wizrad.helloandroid.BuildConfig

import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory

public class RiotServices {

    companion object {

        public val BASE_URL = "https://na.api.pvp.net"
        public val API_KEY  = BuildConfig.RIOT_API_KEY

        public fun create<TService>(klass: Class<TService>) : TService  {
            // construct the builder with the appropriate factories
            val builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // construct a service instance
            return builder.create(klass)
        }
    }
}