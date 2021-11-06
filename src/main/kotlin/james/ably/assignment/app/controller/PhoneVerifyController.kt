package james.ably.assignment.app.controller

import james.ably.assignment.app.dto.GenerateVerifyTokenDTO
import james.ably.assignment.app.dto.MobileVerifyDTO
import james.ably.assignment.app.dto.Response
import james.ably.assignment.app.service.MobileVerifyService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/mobile")
class PhoneVerifyController(
    private val mobileVerifyService: MobileVerifyService
) {

    @PostMapping("/generate")
    fun generateToken(@RequestBody generateVerifyTokenDTO: GenerateVerifyTokenDTO): Response<Boolean> {
        mobileVerifyService.generateToken(generateVerifyTokenDTO.mobile)
        return Response.ok(true)
    }

    @PostMapping("/verify")
    fun verifyPhone(@RequestBody mobileVerifyDTO: MobileVerifyDTO): Response<Boolean> {
        mobileVerifyService.verify(mobileVerifyDTO.forSignUp, mobileVerifyDTO.mobile, mobileVerifyDTO.token)
        return Response.ok(true)
    }
}