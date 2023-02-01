package url.shortener.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import url.shortener.service.BaseConversion

@Configuration
class Config{
	@Bean
	fun getBaseConversion() = BaseConversion()

}