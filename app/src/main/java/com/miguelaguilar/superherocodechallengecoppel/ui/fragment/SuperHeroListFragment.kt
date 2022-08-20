package com.miguelaguilar.superherocodechallengecoppel.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.miguelaguilar.superherocodechallengecoppel.MainApplication
import com.miguelaguilar.superherocodechallengecoppel.R
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroModel
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroResultsModel
import com.miguelaguilar.superherocodechallengecoppel.databinding.FragmentListOfSuperHerosBinding
import com.miguelaguilar.superherocodechallengecoppel.ui.viewmodel.GeneralViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SuperHeroListFragment : Fragment() {
    private lateinit var binding: FragmentListOfSuperHerosBinding
    private val viewModel: GeneralViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListOfSuperHerosBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        setAdapterContext()
        setObservers()
        configRv()
        viewModel.toGetAllHerosData()

        viewModel.superHeroItemAdapter.setClickAction {
            when (it) {
                is SuperHeroResultsModel -> {
                    val bundle = bundleOf("superHeroId" to it.id)
                    this.findNavController()
                        .navigate(R.id.action_superHeroListFragment_to_heroDetailFragment, bundle)

                }
            }
        }

        binding.rvSuperheroList.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val visiblChild = recyclerView.childCount
                val totalItems = recyclerView.layoutManager?.itemCount
                if(!recyclerView.canScrollVertically(1)){
                    MainApplication().changeOffsetQueryNumber()
                    viewModel.toGetAllHerosData()                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

        })

        return binding.root
    }

    private fun setAdapterContext() =
        viewModel.superHeroItemAdapter.setContext(this.requireContext())

    private fun configRv() {
        binding.rvSuperheroList.layoutManager =
            LinearLayoutManager(this.requireContext(), RecyclerView.VERTICAL, false)
        binding.rvSuperheroList.adapter = viewModel.superHeroItemAdapter
    }

    private fun setObservers() {
        viewModel.superHeroModelResponse.observe(this.viewLifecycleOwner) {
            viewModel.superHeroItemAdapter.updateList(it)
        }
    }
}
