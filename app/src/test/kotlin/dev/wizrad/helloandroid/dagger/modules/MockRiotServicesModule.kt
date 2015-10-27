package dev.wizrad.helloandroid.dagger.modules

import dagger.Module
import dagger.Provides
import dev.wizrad.helloandroid.services.LeagueService
import dev.wizrad.helloandroid.services.SummonerService
import org.mockito.Mockito

@Module
public class MockRiotServicesModule {

  @Provides
  fun leagueService() : LeagueService {
    return Mockito.mock(LeagueService::class.java)
  }

  @Provides
  fun summonerService() : SummonerService {
    return Mockito.mock(SummonerService::class.java)
  }
}