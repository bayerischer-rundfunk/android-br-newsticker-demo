package de.br.news.showcase.data

object DataRepository {

    private val newsData = mutableListOf<NewsModel>()

    fun getNewsData(): List<NewsModel> {
        return newsData
    }

    fun onNewNewsData(items: List<NewsModel>) {
        newsData.clear()
        newsData.addAll(items)
    }
}