package james.ably.assignment.app.service

import james.ably.assignment.app.exception.VerifyTokenInvalidException
import mu.KLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class MobileVerifyService (
    private val redisTemplate: RedisTemplate<String, String>,
    private val verifyTokenGenerator: VerifyTokenGenerator,
    @Value("\${mobile.verify.timeout:300}") private val verifyTimeout: Long
        ) {

    companion object: KLogging() {
        const val TOKEN_PREFIX = "MOBILE_TOKEN"
        const val TOKEN_VERIFIED_SIGNUP_PREFIX = "MOBILE_VERIFIED_SIGNUP"
        const val TOKEN_VERIFIED_PWCHANGE_PREFIX = "MOBILE_PWCHANGE_SIGNUP"
        const val VERIFIED_TEXT = "VERIFIED"
    }
    fun generateToken(mobile: String) {
        val opsForVal = redisTemplate.opsForValue()
        val generatedToken = verifyTokenGenerator.generate(6)
        logger.info("token generated for $mobile: $generatedToken")
        opsForVal.set("${TOKEN_PREFIX}:${mobile}", generatedToken, Duration.ofSeconds(verifyTimeout))
    }
    fun verify(signUp: Boolean, mobile: String, token: String) {
        val opsForVal = redisTemplate.opsForValue()
        val generatedToken = opsForVal.get("${TOKEN_PREFIX}:${mobile}")
        if (generatedToken == "" || generatedToken != token) {
            throw VerifyTokenInvalidException("유효하지 않은 인증번호 입니다.")
        }
        opsForVal.set("${TOKEN_PREFIX}:$mobile", "")
        val prefix = if (signUp) TOKEN_VERIFIED_SIGNUP_PREFIX else TOKEN_VERIFIED_PWCHANGE_PREFIX
        opsForVal.set("${prefix}:${mobile}", VERIFIED_TEXT)
    }

    fun isVerified(signUp: Boolean, mobile: String): Boolean {
        val opsForVal = redisTemplate.opsForValue()
        val prefix = if (signUp) TOKEN_VERIFIED_SIGNUP_PREFIX else TOKEN_VERIFIED_PWCHANGE_PREFIX

        return opsForVal.get("${prefix}:${mobile}") == VERIFIED_TEXT
    }
}