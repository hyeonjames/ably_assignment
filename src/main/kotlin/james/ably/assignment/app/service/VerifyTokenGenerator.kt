package james.ably.assignment.app.service

import org.springframework.stereotype.Component
import java.lang.StringBuilder

@Component
class VerifyTokenGenerator {

    fun generate(size: Int): String {
        val builder = StringBuilder()
        for (i in 1..size) {
            builder.append((Math.random()*10).toInt())
        }
        return builder.toString()
    }
}