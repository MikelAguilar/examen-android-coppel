package com.miguelaguilar.superherocodechallengecoppel.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.miguelaguilar.superherocodechallengecoppel.R
import com.miguelaguilar.superherocodechallengecoppel.adapters.viewholders.ItemSuperHeroViewModel
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroModel
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroResultsModel
import com.miguelaguilar.superherocodechallengecoppel.databinding.ItemSuperHeroBinding

class SuperHeroAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var items: MutableList<Any>
    private lateinit var serverResponse: SuperHeroModel
    private lateinit var binding: ItemSuperHeroBinding

    private var listenerClick: ((item: Any) -> Unit)? = null

    fun setClickAction(listenerClick: (item: Any) -> Unit) {
        this.listenerClick = listenerClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_super_hero,
                    parent,
                    false
                )
                return ItemSuperHeroViewHolder(binding, context)
            }
            else -> {
                binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_super_hero,
                    parent,
                    false
                )
                return ItemSuperHeroViewHolder(binding, context)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        when (holder) {
            is ItemSuperHeroViewHolder -> {
                holder.bind(
                    serverResponse.copyright ?: "",
                    items[position] as SuperHeroResultsModel
                )
                Glide.with(context)
                    .load("${(items[position] as SuperHeroResultsModel).thumbnail?.path}.${(items[position] as SuperHeroResultsModel).thumbnail?.extension}")
                    //.onlyRetrieveFromCache(true)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(binding.heroPic)
                binding.root.setOnClickListener { listenerClick?.invoke(items[position]) }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is SuperHeroResultsModel -> 0
            else -> -1
        }
    }

    override fun getItemCount(): Int {
        return if (::items.isInitialized) items.size else 0
    }

    fun updateList(items: SuperHeroModel) {
        this.serverResponse = items
        if(::items.isInitialized){
            if (items.data!!.results!!.isNotEmpty()) {
                listValidator(items.data.results!!)
            }
        }
        else this.items = items.data?.results!!.toMutableList()
        notifyDataSetChanged()
    }

    fun setContext(context: Context) {
        this.context = context
    }

    fun listValidator(heros : List<SuperHeroResultsModel>){
        this.items.addAll(heros)
        items = items.distinctBy { (it as SuperHeroResultsModel).id}.toMutableList()

    }

    class ItemSuperHeroViewHolder(private val binding: ItemSuperHeroBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ItemSuperHeroViewModel()
        fun bind(cpResponse: String, herodata: SuperHeroResultsModel) {
            binding.generalViewModel = viewModel
            viewModel.bind(cpResponse, herodata)
            binding.tvCopyright.text = cpResponse
            binding.tvHeroName.text = herodata.name ?: ""
            binding.tvDescription.text = herodata.description ?: ""
        }
    }

}