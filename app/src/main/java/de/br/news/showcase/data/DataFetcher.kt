package de.br.news.showcase.data

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

class DataFetcher {

    /**
     * REST API Wrapper
     */
    private val BACKEND_REST_URL = "https://newsapp-be-ext.br.de/api/v4/news?limit=100"

    /**
     * Use queries with this endpoint for GraphQL: https://android.api.br24.de/graphql
     */

    fun provideNewsItems(): List<NewsModel>? {
        try {
            val client = OkHttpClient()
            val request = Request.Builder()
                .addHeader("User-Agent", "BR24 News Demo Showcase App")
                .url(BACKEND_REST_URL)
                .build()
            val dataText = client.newCall(request).execute().body?.string()
            dataText?.let {
                return convertJsonToNewsModelList(JSONObject(dataText))
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    private fun convertJsonToNewsModelList(json: JSONObject): List<NewsModel>? {
        val items = mutableListOf<NewsModel>()
        val jsonItems = json.getJSONArray("data")
        for (i in 0 until jsonItems.length()) {
            val jsonItem = jsonItems.getJSONObject(i)
            val item = convertJsonItemToNewsItem(jsonItem)
            items.add(item)
        }
        return if (items.isEmpty()) null else items
    }

    private fun convertJsonItemToNewsItem(json: JSONObject): NewsModel {
        return NewsModel(
            id = json.getString("id"),
            title = json.getString("title").trim(),
            headline = json.getString("headline").trim(),
            image = json.getJSONArray("images").getJSONObject(0).getString("url"),
            publicationDate = json.getString("publicationDate"),
            shareLink = json.getString("shareLink"),
            teaserText = json.getString("teaserText").trim(),
            text = json.getString("text").trim(),
            tags = json.getJSONArray("tags").toStringList()
        )
    }

    /**
     * Note: This is a kotlin extensions function - can be used globally
     * eg.  tags = json.getJSONArray("tags").toStringList()
     */
    fun JSONArray.toStringList(): MutableList<String> {
        val list = mutableListOf<String>()
        for(i in 0 until length()){
            list.add(getString(i))
        }
        return list
    }
}