package de.br.news.showcase.data

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class DataFetcher {

    /**
     * REST API Wrapper
     */
    private val BACKEND_REST_URL = "https://newsapp-be-ext.br.de/api/v4/news?limit=50"

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
            title = json.getString("title")
        )
    }
}