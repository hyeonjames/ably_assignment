package james.ably.assignment.app.service

import james.ably.assignment.app.dto.PasswordChangeDTO
import james.ably.assignment.app.dto.UserDTO
import james.ably.assignment.app.exception.MobileNotVerifiedException
import james.ably.assignment.app.exception.PasswordInvalidException
import james.ably.assignment.app.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService (
    private val currentUserHolder: CurrentUserHolder
        ){
    fun getCurrentUser(): UserDTO {
        return currentUserHolder.getUser().let {
            UserDTO(it.email, it.nickName, it.name, it.mobile)
        }
    }
}