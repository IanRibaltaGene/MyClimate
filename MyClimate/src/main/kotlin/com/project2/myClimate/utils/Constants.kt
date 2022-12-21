package com.project2.myClimate.utils

class Constants {
    companion object{
        private const val URL_API_BASE = "/api"
        private const val URL_API_VERSION = "/v1"
        private const val URL_BASE = URL_API_BASE + URL_API_VERSION

        const val URL_BASE_HOMES = "$URL_BASE/homes"
        const val URL_BASE_SENSORS = "$URL_BASE/sensors"
        const val URL_BASE_USERS = "$URL_BASE/users"
    }
}