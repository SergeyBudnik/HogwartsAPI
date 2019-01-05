package com.bdev.hogwarts_api.filter

import javax.servlet.*
import javax.servlet.http.HttpServletResponse
import java.io.IOException

class CorsFilter : Filter {
    override fun init(filterConfig: FilterConfig) {}

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {

        val response = servletResponse as HttpServletResponse

        response.setHeader("Access-Control-Allow-Origin", "*")
        response.setHeader("Access-Control-Allow-Credentials", "true")
        response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, HEAD, OPTIONS")
        response.setHeader("Access-Control-Allow-Headers", "Authorization, Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers")

        filterChain.doFilter(servletRequest, servletResponse)
    }

    override fun destroy() {}
}