package nl.oug.hr.hrservice

import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import java.io.IOException

@Configuration
class MongoConfig {

    @Bean
    @Throws(IOException::class)
    fun mongoTemplate(): MongoTemplate {
        val mongoClient = EmbeddedMongoFactoryBean().`object` ?: throw IOException()
        return MongoTemplate(mongoClient, "test")
    }
}