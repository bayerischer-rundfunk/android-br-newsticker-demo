package de.br.news.showcase.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsViewModel : ViewModel() {

    val items: LiveData<List<String>> =
        MutableLiveData<List<String>>().apply {
            value = DataRepository().getItemsPage()
        }
}