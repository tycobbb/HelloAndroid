package dev.wizrad.helloandroid.services

import dev.wizrad.helloandroid.models.League
import retrofit.http.GET
import retrofit.http.Path
import rx.Observable

interface LeagueService {

  @GET("/api/lol/{region}/v2.5/league/challenger?type=RANKED_SOLO_5x5")
  public fun fetchChallengerLeague(
    @Path("region") region: String): Observable<League>

}

