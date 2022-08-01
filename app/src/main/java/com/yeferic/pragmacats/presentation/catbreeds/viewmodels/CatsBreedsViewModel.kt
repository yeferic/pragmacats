package com.yeferic.pragmacats.presentation.catbreeds.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yeferic.pragmacats.domain.model.Breed
import com.yeferic.pragmacats.domain.usecases.GetCatsBreedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CatsBreedsViewModel @Inject constructor(
    private val getCatsBreedsUseCase: GetCatsBreedsUseCase
) : ViewModel() {
    val breedSelected = MutableLiveData<Breed>()
    val lsBreeds = MutableLiveData<List<Breed>>()
    val loading = MutableLiveData<Boolean>()
    val filterText = MutableLiveData<String>()
    lateinit var lsFromService : List<Breed>

    init {
        loading.value = false
        breedSelected.value = null
        filterText.value = null
    }

    fun loadBreedsFromService() {
        loading.value = true
        getCatsBreedsUseCase.getAllCatsBreeds()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //lsBreeds.value = it
                lsFromService = it
                filterBreedsList()
                loading.value = false
            }, {
                it.printStackTrace()
                loading.value = false
            })
    }

    fun filterBreedsList(){
        if(filterText.value == null || filterText.value!!.isEmpty()) lsBreeds.value = lsFromService
        else lsBreeds.value = lsFromService?.filter { b -> b.name.lowercase().contains(filterText.value!!.lowercase()) }
    }
}