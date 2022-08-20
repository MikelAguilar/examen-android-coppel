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
import com.miguelaguilar.superherocodechallengecoppel.R
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.GeneralInfoModel
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.ItemModel
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroResultsModel
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroUrlsModel
import com.miguelaguilar.superherocodechallengecoppel.databinding.FragmentLinksListBinding
import com.miguelaguilar.superherocodechallengecoppel.ui.viewmodel.GeneralViewModel
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentLinksList : Fragment() {
    private lateinit var binding: FragmentLinksListBinding
    private val viewModel: GeneralViewModel by viewModels()
    private val linkList : GeneralInfoModel = Hawk.get("link_list", GeneralInfoModel())
    private val linkListUrl : List<SuperHeroUrlsModel>  = Hawk.get("link_list_urls" , emptyList())
    private val from : String = Hawk.get("from", "")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLinksListBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        setAdapterContext()
        configRv()
        populateRv()

        viewModel.itemLinkAdapter.setClickAction {
            when (it) {
                is ItemModel -> {
                    val bundle = bundleOf("webLink" to it.resourceURI)
                    this.findNavController()
                        .navigate(R.id.action_fragmentLinksList_to_webViewFragment, bundle)
                }
                is SuperHeroUrlsModel -> {
                    val bundle = bundleOf("webLink" to it.url)
                    this.findNavController()
                        .navigate(R.id.action_fragmentLinksList_to_webViewFragment, bundle)
                }
            }
        }

        binding.viewModel = viewModel
        return binding.root
    }

    private fun setAdapterContext() =
        viewModel.itemLinkAdapter.setContext(this.requireContext())

    private fun configRv() {
        binding.rvSuperheroList.layoutManager =
            LinearLayoutManager(this.requireContext(), RecyclerView.VERTICAL, false)
        binding.rvSuperheroList.adapter = viewModel.itemLinkAdapter
    }

    private fun populateRv(){
        if(from == "urls") viewModel.itemLinkAdapter.updateList(linkListUrl)
        else viewModel.itemLinkAdapter.updateList(linkList.items!!)
    }
}