package dev.wizrad.helloandroid.services

import dev.wizrad.helloandroid.models.Summoner
import dev.wizrad.helloandroid.services.utilities.UrlComponents

import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

interface SummonerService {

  @GET("/api/lol/{region}/v1.4/summoner/by-name/{summonerNames}")
  public fun fetchSummonersByName(
    @Path("region") region: String,
    @Path("summonerNames") names: UrlComponents<String>): Observable<MutableMap<String, Summoner>>

  @GET("/api/lol/{region}/v1.4/summoner/{summonerIds}")
  public fun fetchSummonersById(
    @Path("region") region: String,
    @Path("summonerIds") ids: UrlComponents<String>): Observable<MutableMap<String, Summoner>>

}

