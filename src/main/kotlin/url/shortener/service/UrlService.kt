package url.shortener.service

import url.shortener.entity.Url

interface UrlService {
    fun getLongUrl(shortUrl: String): String?
    fun createShortUrl(url: String): Url
}