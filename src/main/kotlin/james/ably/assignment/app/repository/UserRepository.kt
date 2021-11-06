package james.ably.assignment.app.repository

import james.ably.assignment.app.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long>{

    fun findByEmail(email: String): User?

    fun existsByMobile(mobile: String): Boolean

    fun existsByEmail(email: String): Boolean
}