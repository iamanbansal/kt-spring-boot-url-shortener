package url.shortener.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import url.shortener.dto.HttpResponse
import url.shortener.entity.Url
import url.shortener.service.UrlService
import java.net.MalformedURLException
import java.net.URI
import java.net.URL

@RestController
@RequestMapping("/")
class UrlController(val urlService: UrlService) {

    @GetMapping
    fun getHomePage(): String {
        return "Landing Page"
    }

    @GetMapping("/{shortUrl}")
    fun redirectUrl(@PathVariable shortUrl: String): ResponseEntity<Void> {
        val longUrl = urlService.getLongUrl(shortUrl)
        return ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create(longUrl))
            .build()
    }

    @PostMapping("/createUrl")
    fun createUrl(@RequestBody url: Url): ResponseEntity<HttpResponse<Url>> {

        try {
            URL(url.longUrl)
        } catch (e: MalformedURLException) {
            return ResponseEntity.badRequest().body(
                HttpResponse(
                    statusCode = HttpStatus.BAD_REQUEST.value(),
                    errorMessage = "Invalid URL"
                )
            )
        }

        val createdUrl = urlService.createShortUrl(url.longUrl!!)

        return ResponseEntity.ok(
            HttpResponse(
                statusCode = HttpStatus.OK.value(),
                body = createdUrl
            )
        )
    }


}