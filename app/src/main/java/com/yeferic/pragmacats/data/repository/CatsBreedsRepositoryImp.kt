package com.yeferic.pragmacats.data.repository

import com.yeferic.pragmacats.data.source.remote.RemoteService
import com.yeferic.pragmacats.domain.model.Breed
import com.yeferic.pragmacats.domain.respository.CatsBreedsRepository
import io.reactivex.Single

class CatsBreedsRepositoryImp(
    private val remoteService: RemoteService
    ) : CatsBreedsRepository {

    override fun getBreeds(): Single<List<Breed>> {
        return remoteService.getBreeds()
    }

}