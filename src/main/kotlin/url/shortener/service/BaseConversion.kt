package url.shortener.service

class BaseConversion {

    private val allowedCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    private val base = allowedCharacters.length

    fun encode(uniqueId: Long): String {
        var input = uniqueId
        val encodedString = StringBuilder()
        if (input == 0L) {
            return allowedCharacters[0].toString()
        }
        while (input > 0) {
            encodedString.append(allowedCharacters[(input % base).toInt()])
            input /= base
        }
        return encodedString.reverse().toString()
    }
}