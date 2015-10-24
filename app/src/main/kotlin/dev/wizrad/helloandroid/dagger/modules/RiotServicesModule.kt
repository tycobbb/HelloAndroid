package dev.wizrad.helloandroid.dagger.modules

import dagger.Module
import dagger.Provides
import dev.wizrad.helloandroid.services.LeagueService
import dev.wizrad.helloandroid.services.RiotServices
import dev.wizrad.helloandroid.services.SummonerService

@Module
class RiotServicesModule() {

  @Provides
  fun leagueService(): LeagueService {
    return RiotServices.create(LeagueService::class.java)
  }

  @Provides
  fun summonerService(): SummonerService {
    return RiotServices.create(SummonerService::class.java)
  }

}