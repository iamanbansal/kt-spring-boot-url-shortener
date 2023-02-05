package url.shortener.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDate

@Entity(name = "Urls")
data class Url(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val longUrl: String? = null,

    val shortUrl: String? = null,

    val createdDate: LocalDate? = null
)