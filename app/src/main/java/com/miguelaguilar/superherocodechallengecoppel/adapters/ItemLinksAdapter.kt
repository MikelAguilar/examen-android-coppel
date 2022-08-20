package com.miguelaguilar.superherocodechallengecoppel.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.miguelaguilar.superherocodechallengecoppel.R
import com.miguelaguilar.superherocodechallengecoppel.adapters.viewholders.ItemLinkViewModel
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.ItemModel
import com.miguelaguilar.superherocodechallengecoppel.data.model.response.SuperHeroUrlsModel
import com.miguelaguilar.superherocodechallengecoppel.databinding.ItemListLinksBinding

class ItemLinksAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var items: MutableList<Any>
    private lateinit var binding: ItemListLinksBinding

    private var listenerClick: ((item: Any) -> Unit)? = null

    fun setClickAction(listenerClick: (item: Any) -> Unit) {
        this.listenerClick = listenerClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> {
                binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_list_links,
                    parent,
                    false
                )
                return ItemLinksViewHolder(binding)
            }
            else -> {
                binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_list_links,
                    parent,
                    false
                )
                return ItemLinksViewHolder(binding)
            }
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        when (holder) {
            is ItemLinksViewHolder -> {
                when(items[position]){
                    is ItemModel -> {
                        holder.bind(items[position] as ItemModel)
                    }
                    is SuperHeroUrlsModel -> {
                        holder.bindUrls(items[position] as SuperHeroUrlsModel)
                    }
                }
                binding.root.setOnClickListener { listenerClick?.invoke(items[position]) }
            }


        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ItemModel -> 0
            is SuperHeroUrlsModel -> 1
            else -> -1
        }
    }

    override fun getItemCount(): Int {
        return if (::items.isInitialized) items.size else 0
    }

    fun updateList(items: List<Any>) {
        items.let{ itemsLet ->
            when(itemsLet.first()){
                is ItemModel -> this.items = itemsLet.toMutableList()
                is SuperHeroUrlsModel -> this.items = itemsLet.toMutableList()
                }
            /*itemsLet.forEach {
                when (it){
                    is ItemModel -> {
                        this.items.add(it)
                    }
                    is SuperHeroUrlsModel-> {
                        this.items.add(it)
                    }
                }
            }*/
        }
        notifyDataSetChanged()

    }

    fun setContext(context: Context) {
        this.context = context
    }

    class ItemLinksViewHolder(private val binding: ItemListLinksBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = ItemLinkViewModel()
        fun bind(herodata: ItemModel) {
            binding.viewModel = viewModel
            viewModel.bind(herodata)
            binding.tvLink.text = herodata.resourceURI ?: ""
            binding.tvName.text = herodata.name ?: ""
        }
        fun bindUrls(heroUrls : SuperHeroUrlsModel){
            binding.viewModel = viewModel
            viewModel.bindUrls(heroUrls)
            binding.tvName.text = heroUrls.type ?: ""
            binding.tvLink.text = heroUrls.url ?: ""
        }
    }
}