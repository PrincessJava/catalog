package ru.petproject.catalog.config

import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse


@Component
class RequestLoggingFilter: Filter {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain) {
        Context.requestId = UUID.randomUUID().toString()
        chain.doFilter(request, response)
    }
}
