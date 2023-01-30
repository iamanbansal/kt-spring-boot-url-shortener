package url.shortener.repository

import url.shortener.entity.Url
import org.springframework.data.jpa.repository.JpaRepository

interface UrlRepository : JpaRepository<Url, Long> {
    public fun findByShortUrl(shortUrl: String): Url?
}