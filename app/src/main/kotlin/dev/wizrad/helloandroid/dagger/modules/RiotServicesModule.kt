package dev.wizrad.helloandroid.dagger.modules

import dev.wizrad.helloandroid.services.LeagueService
import dev.wizrad.helloandroid.services.SummonerService
import dev.wizrad.helloandroid.services.RiotServices

import dagger.Module
import dagger.Provides

@Module
public class RiotServicesModule() : RiotServicesProvider {

    @Provides
    override fun leagueService() : LeagueService {
        return RiotServices.create(LeagueService::class.java)
    }

    @Provides
    override fun summonerService() : SummonerService {
        return RiotServices.create(SummonerService::class.java)
    }

}