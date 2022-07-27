package com.yeferic.pragmacats.domain.respository

import com.yeferic.pragmacats.domain.model.Breed
import io.reactivex.Single

interface CatsBreedsRepository {
    fun getBreeds(): Single<List<Breed>>
}