package de.br.news.showcase.data

data class NewsModel(
    val id: String,
    val title: String,
    val headline: String,
    val image: String,
    val publicationDate: String,
    val shareLink: String,
    val teaserText: String,
    val text: String,
    val tags: List<String>
)