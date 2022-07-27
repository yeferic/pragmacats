package com.yeferic.pragmacats.domain.usecases

import com.yeferic.pragmacats.domain.model.Breed
import com.yeferic.pragmacats.domain.respository.CatsBreedsRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCatsBreedsUseCase @Inject constructor(
    private val repository: CatsBreedsRepository
    ){

    fun getAllCatsBreeds() : Single<List<Breed>> {
        return repository.getBreeds()
    }
}