package james.ably.assignment.app.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import james.ably.assignment.app.common.UserPrincipal
import james.ably.assignment.app.model.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtTokenProvider (
    @Value("\${jwt.secret}") secretKey: String,
    @Value("\${jwt.token.timeout}") private val tokenTimeoutSeconds: Long
        ) {
    private val secretKey64 = Base64.getEncoder().encodeToString(secretKey.toByteArray())

    fun create(user: User): String {
        val claims = Jwts.claims().setSubject(user.id.toString())
        val now = Date()
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + tokenTimeoutSeconds*1000L))
            .signWith(SignatureAlgorithm.HS256, secretKey64)
            .compact()
    }
    fun getId(token: String): Long {
        return Jwts.parser().setSigningKey(secretKey64).parseClaimsJws(token).body.subject.toLong()
    }

    fun getPrincipal(token: String): UserPrincipal {
        return UserPrincipal(getId(token))
    }

    fun isValid(token: String): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(secretKey64).parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }
}