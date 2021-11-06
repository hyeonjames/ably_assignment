package james.ably.assignment.app.controller

import james.ably.assignment.app.dto.*
import james.ably.assignment.app.exception.MobileNotVerifiedException
import james.ably.assignment.app.service.AuthService
import james.ably.assignment.app.service.MobileVerifyService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/auth")
class AuthController (
    private val authService: AuthService,
    private val mobileVerifyService: MobileVerifyService
        ) {

    @PostMapping("/signin")
    fun login(@RequestBody signInDTO: SignInDTO): Response<String> {

        return Response.ok(authService.signin(signInDTO))
    }

    @PostMapping("/signup")
    fun signup(@Valid @RequestBody signUpDTO: SignUpDTO): Response<Boolean> {
        if (!mobileVerifyService.isVerified(true, signUpDTO.mobile)) {
            throw MobileNotVerifiedException("인증되지 않은 번호 입니다.")
        }
        authService.signup(signUpDTO)
        return Response.ok(true)
    }


    @PostMapping("/change/password")
    fun changePassword(@Valid @RequestBody passwordChangeDTO: PasswordChangeDTO): Response<Boolean> {
        authService.passwordChange(passwordChangeDTO)
        return Response.ok(true)
    }
}