package com.yeferic.pragmacats.presentation.catbreeds.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.yeferic.pragmacats.R
import com.yeferic.pragmacats.common.loadImageFromUrl
import com.yeferic.pragmacats.databinding.FragmentCatsBreedsBinding
import com.yeferic.pragmacats.databinding.FragmentDetailBinding
import com.yeferic.pragmacats.presentation.catbreeds.viewmodels.CatsBreedsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: CatsBreedsViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        initViewModelHandlers()
        initWidgetsHandlers()
        return binding.root
    }
    fun initViewModelHandlers(){
        binding.item = viewModel.breedSelected.value
    }
    fun initWidgetsHandlers(){
        binding.btnBack.setOnClickListener {
            this@DetailFragment.activity?.supportFragmentManager?.popBackStack()
        }

        viewModel.breedSelected.value?.image?.let {
            binding.imgBreedDetail.loadImageFromUrl(it.url)
        }
    }

    companion object {
        val FRAGMENT_NAME =DetailFragment::class.java.name
        @JvmStatic
        fun newInstance() =
            DetailFragment().apply {
                arguments = Bundle().apply {  }
            }
    }
}