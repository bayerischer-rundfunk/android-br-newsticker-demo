package de.br.news.showcase.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import de.br.news.showcase.R
import de.br.news.showcase.data.NewsModel
import de.br.news.showcase.databinding.ItemViewBinding
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat

import androidx.core.content.ContextCompat.startActivity




class NewsItemsViewAdapter : RecyclerView.Adapter<NewsItemsViewAdapter.ItemViewHolder>() {

    private val items: MutableList<NewsModel> = mutableListOf()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.apply {
            setOnClickListener {
                val shareUrl = items[position].shareLink
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(shareUrl)))
            }
        }
    }

    fun update(newItems: List<NewsModel>) {
        val diff = NewsItemDiffCallback(items, newItems)
        val result = DiffUtil.calculateDiff(diff)
        result.dispatchUpdatesTo(this)

        items.clear()
        items.addAll(newItems)
    }

    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(items: List<NewsModel>) {
            val adapter = adapter as NewsItemsViewAdapter
            adapter.update(items)
        }
    }

    class ItemViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemViewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_view,
            parent,
            false
        )
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NewsModel) {
            binding.item = item
        }
    }
}