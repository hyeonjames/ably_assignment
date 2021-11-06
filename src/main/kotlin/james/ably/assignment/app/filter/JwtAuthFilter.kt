package james.ably.assignment.app.filter

import james.ably.assignment.app.service.JwtTokenProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtAuthFilter (
    private val jwtTokenProvider: JwtTokenProvider
        ): GenericFilterBean() {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val token = (request as HttpServletRequest).getHeader("X-AUTH-TOKEN")
        token?.let {
            if (jwtTokenProvider.isValid(it)) {
                val auth = UsernamePasswordAuthenticationToken(jwtTokenProvider.getPrincipal(token), "", emptyList())
                SecurityContextHolder.getContext().authentication = auth
            }
        }
        chain?.doFilter(request, response)
    }

}