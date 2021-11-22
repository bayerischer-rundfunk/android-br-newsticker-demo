package de.br.news.showcase.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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

        val viewModel = ViewModelProvider(this)[NewsViewModel::class.java]
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setupRecyclerview(binding)
        setupRefresher(binding)
        setupReload(binding)

        return binding.root
    }

    private fun setupRecyclerview(binding: FragmentNewsBinding) {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = NewsItemsViewAdapter()
        }
    }

    private fun setupRefresher(binding: FragmentNewsBinding) {
        binding.refresher.apply {
            setColorSchemeResources(R.color.primaryColor)
            setOnRefreshListener {
                binding.viewModel?.fetchData()
            }
        }
    }

    private fun setupReload(binding: FragmentNewsBinding) {
        binding.reloadButton.apply {
            setOnClickListener {
                binding.viewModel?.fetchData()
            }
        }
    }
}