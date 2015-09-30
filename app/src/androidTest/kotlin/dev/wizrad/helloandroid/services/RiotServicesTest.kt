package dev.wizrad.helloandroid.services

import dev.wizrad.helloandroid.BuildConfig
import kotlin.test.assertEquals
import android.test.AndroidTestCase
import dev.wizrad.helloandroid.services.utilities.LoggingInterceptor
import kotlin.test.assertNotNull

public class RiotServicesTest : AndroidTestCase() {

    fun test_hasAnApiKey() {
        assertEquals(RiotServices.API_KEY, BuildConfig.RIOT_API_KEY)
    }

    fun test_createsLeagueService() {
        val service = RiotServices.create(LeagueService::class.java)
        assertNotNull(service, "failed to instantiate a LeagueService")
    }

    fun test_createsSummonerService() {
        val service = RiotServices.create(SummonerService::class.java)
        assertNotNull(service, "failed to instantiate a SummonerService")
    }

}