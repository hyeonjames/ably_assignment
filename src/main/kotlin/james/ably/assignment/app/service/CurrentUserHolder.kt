package james.ably.assignment.app.service

import james.ably.assignment.app.common.UserPrincipal
import james.ably.assignment.app.model.User
import james.ably.assignment.app.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class CurrentUserHolder (
    val userRepository: UserRepository
        ){
    fun getUser(): User {
        println(SecurityContextHolder.getContext().authentication.principal)
        val userPrincipal = SecurityContextHolder.getContext().authentication.principal as UserPrincipal
        return userRepository.getById(userPrincipal.id)
    }
}