package de.br.news.showcase.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.br.news.showcase.R
import de.br.news.showcase.data.NewsViewModel
import de.br.news.showcase.databinding.FragmentNewsBinding

class NewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentNewsBinding>(
            inflater,
            R.layout.fragment_news,
            container,
            false
        )

        binding.lifecycleOwner = this

        loadRecyclerView(binding)

        return binding.root
    }

    private fun loadRecyclerView(binding: FragmentNewsBinding) {
        val viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        binding.viewModel = viewModel

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = NewsItemsViewAdapter()
        }
    }
}