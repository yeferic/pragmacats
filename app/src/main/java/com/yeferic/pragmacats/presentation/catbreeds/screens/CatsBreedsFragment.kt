package com.yeferic.pragmacats.presentation.catbreeds.screens

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.yeferic.pragmacats.R
import com.yeferic.pragmacats.databinding.FragmentCatsBreedsBinding
import com.yeferic.pragmacats.domain.model.Breed
import com.yeferic.pragmacats.presentation.catbreeds.adapters.CatsBreedsAdapter
import com.yeferic.pragmacats.presentation.catbreeds.adapters.IOnCatsBreedsAdapterListener
import com.yeferic.pragmacats.presentation.catbreeds.viewmodels.CatsBreedsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatsBreedsFragment : Fragment() , IOnCatsBreedsAdapterListener {

    private lateinit var binding: FragmentCatsBreedsBinding
    private val viewModel: CatsBreedsViewModel by activityViewModels()
    private lateinit var lsAdapter : CatsBreedsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewModel){
            loadBreedsFromService()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cats_breeds, container, false)
        binding.lifecycleOwner = this@CatsBreedsFragment
        initWidgetsHandlers()
        initViewModelHandler()

        return binding.root
    }

    private fun initViewModelHandler() {
        viewModel.loading.observe(
            viewLifecycleOwner,
            Observer {
                binding.pbSearch.visibility = if (it) View.VISIBLE else View.INVISIBLE
            }
        )

        viewModel.lsBreeds.observe(
            viewLifecycleOwner,
            Observer {
                it?.let { ls ->
                    addListBreeds(ls)
                }
            }
        )
    }

    private fun initWidgetsHandlers() {
        lsAdapter = CatsBreedsAdapter(this@CatsBreedsFragment)
        binding.lsCatsBreeds.adapter = lsAdapter
        binding.lsCatsBreeds.layoutManager = LinearLayoutManager(this@CatsBreedsFragment.activity)

        binding.txtFilter.setText(viewModel.filterText.value)

        binding.txtFilter.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    //TODO Filter
                    viewModel.filterText.value = binding.txtFilter.text.toString()
                    true
                }
                else -> false
            }
        }

        binding.btnSearch.setOnClickListener { _ ->
            //TODO
            viewModel.filterText.value = binding.txtFilter.text.toString()
        }
    }

    private fun addListBreeds(ls: List<Breed>){
        lsAdapter?.addData(ls)
    }

    override fun showBreedDetail(b: Breed) {
        viewModel.breedSelected.value = b
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.fragmentContainer,
                DetailFragment.newInstance(),
                DetailFragment.FRAGMENT_NAME
            )
            .addToBackStack(DetailFragment.FRAGMENT_NAME)
            .commitAllowingStateLoss()
    }

    companion object {
        val FRAGMENT_NAME = CatsBreedsFragment::class.java.name

        @JvmStatic
        fun newInstance() =
            CatsBreedsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}