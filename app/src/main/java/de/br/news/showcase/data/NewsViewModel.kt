package de.br.news.showcase.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel : ViewModel() {

    val fetchState: MutableLiveData<FetchState> = MutableLiveData(FetchState.IDLE)

    val items: MutableLiveData<List<NewsModel>> =
        MutableLiveData<List<NewsModel>>().apply {
            value = DataRepository.getNewsData()
            fetchData()
        }

    fun fetchData() {
        viewModelScope.launch {
            fetchState.value = FetchState.FETCHING
            val newItems = withContext(Dispatchers.IO) {
                DataFetcher().provideNewsItems()
            }
            if (newItems == null) {
                fetchState.value = FetchState.ERROR
            } else {
                DataRepository.onNewNewsData(newItems)
                items.value = newItems
                fetchState.value = FetchState.IDLE
            }
        }
    }
}