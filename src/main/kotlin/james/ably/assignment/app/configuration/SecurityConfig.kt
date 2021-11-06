package james.ably.assignment.app.configuration

import james.ably.assignment.app.filter.JwtAuthFilter
import james.ably.assignment.app.service.JwtTokenProvider
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtTokenProvider: JwtTokenProvider
): WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        http!!
            .cors().and()
            .csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            .antMatchers("/api/v1/auth/**").anonymous()
            .antMatchers("/api/v1/mobile/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(JwtAuthFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)
            .formLogin().disable()
    }
}