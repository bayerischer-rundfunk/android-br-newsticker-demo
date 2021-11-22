package de.br.news.showcase.data

import okhttp3.internal.format
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*


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
) {
    private val SERVER_TIME_FORMAT = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.UK)
    private val USER_TIME_FORMAT = SimpleDateFormat("EE dd.MM. HH:mm 'Uhr'", Locale.GERMAN)


    fun getPublicationDateText(): String? {
        val timeFormat = SERVER_TIME_FORMAT
        timeFormat.timeZone = TimeZone.getTimeZone("GMT")
        val date = SERVER_TIME_FORMAT.parse(publicationDate)
        return USER_TIME_FORMAT.format(date)
    }

}


