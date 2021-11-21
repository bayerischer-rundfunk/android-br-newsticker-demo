package de.br.news.showcase.data

class DataRepository {

    private val newsData = mutableListOf<NewsModel>()

    fun getNewsData(): List<NewsModel> {
        newsData.clear()
        newsData.add(NewsModel("", "Test 1"))
        newsData.add(NewsModel("", "Test 2"))

        return newsData
    }
}