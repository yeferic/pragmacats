package com.yeferic.pragmacats.data.source.remote

import com.yeferic.pragmacats.domain.model.Breed
import io.reactivex.Single
import retrofit2.http.GET

interface RemoteService {

    @GET("breeds")
    fun getBreeds(): Single<List<Breed>>
}