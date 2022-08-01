package com.yeferic.pragmacats.presentation.catbreeds.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.yeferic.pragmacats.R
import com.yeferic.pragmacats.common.loadImageFromUrl
import com.yeferic.pragmacats.databinding.BreedItemListBinding
import com.yeferic.pragmacats.domain.model.Breed

class CatsBreedsAdapter(val listener:IOnCatsBreedsAdapterListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val lsBreeds: MutableList<Breed> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bindig = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context), R.layout.breed_item_list, parent, false
        )
        return BreedViewHolder(bindig)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as BreedViewHolder).onBind(getItem(position))
    }

    private fun getItem(position: Int): Breed {
        return lsBreeds[position]
    }

    override fun getItemCount(): Int {
        return lsBreeds.size
    }

    fun addData(list: List<Breed>) {
        this.lsBreeds.clear()
        this.lsBreeds.addAll(list)
        notifyDataSetChanged()
    }

    inner class BreedViewHolder(
        private val dataBinding: ViewDataBinding
    ) : RecyclerView.ViewHolder(dataBinding.root) {

        fun onBind(breed: Breed) {
            val binding = dataBinding as BreedItemListBinding
            binding.listItem = breed
            binding.imgBreed.loadImageFromUrl(breed.image.url)
            itemView.setOnClickListener {
               listener.showBreedDetail(breed)
            }
        }
    }
}