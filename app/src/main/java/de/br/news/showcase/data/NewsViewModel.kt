package de.br.news.showcase.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsViewModel : ViewModel() {

    val items: LiveData<List<NewsModel>> =
        MutableLiveData<List<NewsModel>>().apply {
            value = DataRepository().getNewsData()
        }


}