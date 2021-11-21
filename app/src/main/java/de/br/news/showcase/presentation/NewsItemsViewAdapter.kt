package de.br.news.showcase.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import de.br.news.showcase.R
import de.br.news.showcase.databinding.ItemViewBinding

class NewsItemsViewAdapter : RecyclerView.Adapter<NewsItemsViewAdapter.ItemViewHolder>() {

    private val items: MutableList<String> = mutableListOf()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun update(newItems: List<String>) {
        val diff = NewsItemDiffCallback(items, newItems)
        val result = DiffUtil.calculateDiff(diff)
        result.dispatchUpdatesTo(this)

        items.clear()
        items.addAll(newItems)
    }

    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(items: List<String>) {
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

        fun bind(item: String) {
            binding.item = item
        }
    }
}