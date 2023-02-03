package url.shortener.controller

import jakarta.servlet.http.HttpServletRequest
import kotlinx.html.*
import kotlinx.html.stream.createHTML
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

        return createHTML(true).html {
            head {
                title {
                    +"Welcome to URL Shortener"
                }
            }
            body {
                form(
                    action = "result",
                    method = FormMethod.post,
                ) {
                    label {
                        input(
                            type = InputType.url,
                            name = "longUrl",
                        ) {
                            placeholder = "Enter your url"
                            required = true
                        }
                    }

                    button(
                        classes = "primary",
                        type = ButtonType.submit,
                    ) {
                        +"Shorten"
                    }
                }
            }
        }
    }

    @GetMapping("/urls")
    fun getAllUrls(): List<Url> {
       return urlService.getAllUrls()
    }

    @GetMapping("/{shortUrl}")
    fun redirectUrl(@PathVariable shortUrl: String): ResponseEntity<Void> {
        val longUrl = urlService.getLongUrl(shortUrl)
        return ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create(longUrl))
            .build()
    }

    @PostMapping("/createUrl")
    fun createUrl(@RequestBody url: Url, request: HttpServletRequest): ResponseEntity<HttpResponse<Url>> {
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
        val shortUrl = generateUrl(createdUrl, request)

        return ResponseEntity.ok(
            HttpResponse(
                statusCode = HttpStatus.OK.value(),
                body = createdUrl.copy(shortUrl =  shortUrl)
            )
        )
    }

    @PostMapping("/result")
    fun createUrlForm(@ModelAttribute("userFormData") url: Url, request: HttpServletRequest): String {

        val createdUrl = urlService.createShortUrl(url.longUrl!!)
        val shortUrl = generateUrl(createdUrl, request)
        return createHTML(true).html {
            body {
                p {
                    +"Short url is: "
                    this.a(href = shortUrl) {
                        +shortUrl
                    }

                }
            }
        }
    }

    private fun generateUrl(url: Url, request: HttpServletRequest): String {
        return "${request.scheme}://${request.serverName}:${request.serverPort}/${url.shortUrl}"
    }
}