package dev.wizrad.helloandroid.services
import  dev.wizrad.helloandroid.models.League

import rx.Observable
import retrofit.http.GET
import retrofit.http.Path

public interface LeagueService {

    @GET("/api/lol/{region}/v2.5/league/challenger?type=RANKED_SOLO_5x5&api_key=${RiotServices.API_KEY}")
    public fun fetchChallengerLeague(@Path("region") region: String) : Observable<League>

}

