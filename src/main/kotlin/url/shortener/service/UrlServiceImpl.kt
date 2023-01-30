package url.shortener.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import url.shortener.entity.Url
import url.shortener.repository.UrlRepository
import java.time.LocalDate


@Service
class UrlServiceImpl @Autowired constructor(
    private val urlRepository: UrlRepository,
    private val baseConversion: BaseConversion
) : UrlService {
    override fun getLongUrl(shortUrl: String): String? {
        val longUrl = urlRepository.findByShortUrl(shortUrl)
        return longUrl?.longUrl
    }

    override fun createShortUrl(url: String): Url {
        val dbUrl = urlRepository.findByShortUrl(url)
        if (dbUrl != null) {
            return dbUrl
        }

        val savedUrl = urlRepository.save(
            Url(longUrl = url, createdDate = LocalDate.now())
        )

        val base62 = baseConversion.encode(savedUrl.id!!)

        return urlRepository.save(savedUrl.copy(shortUrl = base62))
    }
}