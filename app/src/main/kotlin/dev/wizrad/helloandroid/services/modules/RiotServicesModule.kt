package dev.wizrad.helloandroid.services.modules

import dev.wizrad.helloandroid.BaseApplication
import dev.wizrad.helloandroid.services.LeagueService
import dev.wizrad.helloandroid.services.SummonerService

import dagger.Module
import dagger.Provides

@Module
public class RiotServicesModule(
    private val application: BaseApplication) {

    @Provides
    fun providesLeagueService() : LeagueService {
        return RiotServices.create(LeagueService::class.java)
    }

    @Provides
    fun providesSummonerService() : SummonerService {
        return RiotServices.create(SummonerService::class.java)
    }

}