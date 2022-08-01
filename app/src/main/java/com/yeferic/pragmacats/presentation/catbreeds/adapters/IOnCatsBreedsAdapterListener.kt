package com.yeferic.pragmacats.presentation.catbreeds.adapters

import com.yeferic.pragmacats.domain.model.Breed

interface IOnCatsBreedsAdapterListener {
    fun showBreedDetail(b: Breed)
}