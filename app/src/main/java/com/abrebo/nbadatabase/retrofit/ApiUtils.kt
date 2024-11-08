package com.abrebo.nbadatabase.retrofit

class ApiUtils {
    companion object{
        val BASE_URL="https://raw.githubusercontent.com/SemihGul5/Sofifa-Data-Fetcher/refs/heads/main/"
        fun getRosterDao():RosterDao{
            return  RetrofitClient.getClient(BASE_URL).create(RosterDao::class.java)
        }
    }
}