package com.miguelaguilar.superherocodechallengecoppel.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.miguelaguilar.superherocodechallengecoppel.R
import com.miguelaguilar.superherocodechallengecoppel.databinding.FragmentHeroDetailsBinding
import com.miguelaguilar.superherocodechallengecoppel.ui.viewmodel.GeneralViewModel
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroDetailFragment : Fragment() {
    private lateinit var binding: FragmentHeroDetailsBinding
    private val viewModel: GeneralViewModel by viewModels()
    private var heroId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeroDetailsBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        heroId = arguments?.getInt("superHeroId") ?: -1
        setObservers()

        viewModel.toGetSpecificHeroData(heroId)

        return binding.root
    }

    private fun setObservers() {
        viewModel.superHeroModelResponse.observe(this.viewLifecycleOwner) {
            it.data?.results.let { heroResultItem ->
                heroResultItem?.map { itemHero ->
                    Glide.with(this)
                        .load("${itemHero.thumbnail?.path}.${itemHero.thumbnail?.extension}")
                        .onlyRetrieveFromCache(true)
                        .skipMemoryCache(false)
                        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                        .into(binding.heroPic)
                    binding.heroName.text = itemHero.name ?: ""
                    binding.heroDescription.text = itemHero.description ?: ""
                    binding.btnComics.setOnClickListener {
                        Hawk.deleteAll()
                        Hawk.put(
                            "link_list",
                            itemHero.comics
                        )
                        Hawk.put("from", "comics")
                        this.findNavController().navigate(R.id.action_heroDetailFragment_to_fragmentLinksList)
                    }
                    binding.btnSeries.setOnClickListener {
                        Hawk.deleteAll()
                        Hawk.put(
                            "link_list",
                            itemHero.series
                        )
                        Hawk.put("from", "series")
                        this.findNavController().navigate(R.id.action_heroDetailFragment_to_fragmentLinksList)
                    }
                    binding.btnEvents.setOnClickListener {
                        Hawk.deleteAll()
                        Hawk.put(
                            "link_list",
                            itemHero.events
                        )
                        Hawk.put("from", "events")
                        this.findNavController().navigate(R.id.action_heroDetailFragment_to_fragmentLinksList)
                    }
                    binding.btnUrls.setOnClickListener {
                        Hawk.deleteAll()
                        Hawk.put(
                            "link_list_urls",
                            itemHero.urls
                        )
                        Hawk.put("from", "urls")
                        this.findNavController().navigate(R.id.action_heroDetailFragment_to_fragmentLinksList)
                    }
                    binding.btnStories.setOnClickListener {
                        Hawk.deleteAll()
                        Hawk.put(
                            "link_list",
                            itemHero.stories
                        )
                        Hawk.put("from", "stories")
                        this.findNavController().navigate(R.id.action_heroDetailFragment_to_fragmentLinksList)
                    }
                }

            }
        }
    }

}