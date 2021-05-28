package ru.testproject.catalog

import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.boot.autoconfigure.SpringBootApplication
import kotlin.jvm.JvmStatic
import org.springframework.boot.SpringApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaAuditing
@SpringBootApplication
open class CatalogApplication

fun main(args: Array<String>) {
    SpringApplication.run(CatalogApplication::class.java, *args)
}

