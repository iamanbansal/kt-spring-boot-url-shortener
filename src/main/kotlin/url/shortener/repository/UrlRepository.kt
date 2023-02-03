package url.shortener.repository

import org.springframework.data.jpa.repository.JpaRepository
import url.shortener.entity.Url

interface UrlRepository : JpaRepository<Url, Long> {
    public fun findByShortUrl(shortUrl: String): Url?
    public fun findByLongUrl(longUrl: String): Url?
}