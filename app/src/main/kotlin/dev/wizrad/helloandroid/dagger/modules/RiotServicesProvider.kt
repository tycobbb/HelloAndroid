package dev.wizrad.helloandroid.dagger.modules

import dev.wizrad.helloandroid.services.LeagueService
import dev.wizrad.helloandroid.services.SummonerService

interface RiotServicesProvider {
    fun leagueService()   : LeagueService
    fun summonerService() : SummonerService
}