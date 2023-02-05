package url.shortener.config

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import url.shortener.repository.UrlRepository
import url.shortener.service.BaseConversion
import url.shortener.service.UrlService
import url.shortener.service.UrlServiceImpl


@Configuration
class Config {
    @Bean
    fun getBaseConversion() = BaseConversion()

//    @Bean
//    fun getDataSource(): DataSource {
//        val dataSourceBuilder = DataSourceBuilder.create()
//        dataSourceBuilder.driverClassName("org.postgresql.Driver")
//        dataSourceBuilder.url("jdbc:postgresql://localhost:5332/urlshortener")
//        dataSourceBuilder.username("amanbansal")
//        dataSourceBuilder.password("password")
//        return dataSourceBuilder.build()
//
//    }
}