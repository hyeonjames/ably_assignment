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
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val currentUserHolder: CurrentUserHolder,
    private val mobileVerifyService: MobileVerifyService
        ){
    fun getCurrentUser(): UserDTO {
        return currentUserHolder.getUser().let {
            UserDTO(it.email, it.nickName, it.name, it.mobile)
        }
    }
    fun passwordChange(passwordChangeDTO: PasswordChangeDTO) {
        val user = currentUserHolder.getUser()
        if (!passwordEncoder.matches(passwordChangeDTO.oldPassword, user.encPassword)) {
            throw PasswordInvalidException("이전 비밀번호가 올바르지 않습니다.")
        }
        if (!mobileVerifyService.isVerified(false, user.mobile)) {
            throw MobileNotVerifiedException("전화번호 인증이 필요합니다.")
        }
        user.encPassword = passwordEncoder.encode(passwordChangeDTO.newPassword)
        userRepository.save(user)
    }
}