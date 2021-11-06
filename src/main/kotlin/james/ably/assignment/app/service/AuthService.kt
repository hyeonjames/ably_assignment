package james.ably.assignment.app.service

import james.ably.assignment.app.dto.SignInDTO
import james.ably.assignment.app.dto.SignUpDTO
import james.ably.assignment.app.exception.AlreadySignedUpException
import james.ably.assignment.app.exception.PasswordInvalidException
import james.ably.assignment.app.exception.UserNotFoundException
import james.ably.assignment.app.model.User
import james.ably.assignment.app.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
) {
    fun signin(request: SignInDTO): String {
        val user = userRepository.findByEmail(request.email) ?: throw UserNotFoundException("해당 하는 이메일로 가입한 유저 정보를 찾을 수 없습니다.")
        if (!passwordEncoder.matches(request.password, user.encPassword)) {
            throw PasswordInvalidException("올바르지 않은 비밀번호 입니다.")
        }
        return jwtTokenProvider.create(user)
    }
    fun signup(request: SignUpDTO) {
        if (userRepository.existsByEmail(request.email)) {
            throw AlreadySignedUpException("이미 가입된 이메일 입니다.")
        }
        if (userRepository.existsByMobile(request.mobile)) {
            throw AlreadySignedUpException("이미 가입된 전화번호 입니다.")
        }
        val user = request.run {
            User(
                null,
                email = email,
                name = name,
                encPassword = passwordEncoder.encode(password),
                nickName = nickName,
                mobile = mobile
            )
        }
        userRepository.save(user)
    }
}