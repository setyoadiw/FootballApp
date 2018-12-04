package com.setyo.kotlin.footballmatchschedule5.network

import com.setyo.kotlin.footballmatchschedule5.BuildConfig

object Network {

        //event

        fun getLastSchedule(league: String?): String{
                return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventspastleague.php?id="+league
        }

        fun getNextSchedule(league: String?): String{
                return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/eventsnextleague.php?id="+league
        }

        fun getScheduleBySearch(key: String?): String{
                return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchevents.php?e=" +key
        }

        //team

        fun getTeamList(league: String?): String{
            return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/search_all_teams.php?l="+league
        }

        fun getTeamDetail(teamId: String?): String{
                return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id="+teamId
        }

        fun getTeamSearch(key: String?): String{
                return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/searchteams.php?t=" + key
        }

        //player

        fun getPlayerList(player: String?): String{
                return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookup_all_players.php?id=" + player
        }




}