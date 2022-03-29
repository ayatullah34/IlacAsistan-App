package com.can.ilacasistani.servis

import com.can.ilacasistani.model.Ilac
import io.reactivex.Single
import retrofit2.http.GET

interface IlacAPI {
    //GET,POST

    //https://raw.githubusercontent.com/ayatullah34/IlacAsistani/main/ilaclar.json
    //BASE URL -> https://raw.githubusercontent.com/

    @GET("ayatullah34/IlacAsistani/main/ilaclar.json")
    fun getIlac() : Single<List<Ilac>>
}