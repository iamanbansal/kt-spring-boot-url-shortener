package url.shortener.dto

data class HttpResponse<T>(
    val statusCode: Int,
    val body: T? = null,
    val errorMessage: String? = null
)