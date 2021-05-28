package ru.petproject.catalog

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
open class CatalogApplication

fun main(args: Array<String>) {
    SpringApplication.run(CatalogApplication::class.java, *args)
}

