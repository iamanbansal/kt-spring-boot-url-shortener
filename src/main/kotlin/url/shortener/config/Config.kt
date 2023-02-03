package url.shortener.config

import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import url.shortener.service.BaseConversion
import javax.sql.DataSource


@Configuration
class Config {
	@Bean
	fun getBaseConversion() = BaseConversion()
}