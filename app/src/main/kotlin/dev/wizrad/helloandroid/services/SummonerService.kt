package dev.wizrad.helloandroid.services
import  dev.wizrad.helloandroid.models.Summoner

import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

public interface SummonerService {

    @GET("/api/lol/{region}/v1.4/summoner/by-name/{summonerNames}")
    public fun fetchSummonersByName(
        @Path("region") region: String,
        @Path("summonerNames") names: List<String>) : Observable<Map<String, Summoner>>

    @GET("/api/lol/{region}/v1.4/summoner/{summonerIds}")
    public fun fetchSummonersById(
        @Path("region") region: String,
        @Path("summonerIds") ids: List<String>) : Observable<Map<String, Summoner>>

}

