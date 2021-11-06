package james.ably.assignment.app.controller

import james.ably.assignment.app.dto.PasswordChangeDTO
import james.ably.assignment.app.dto.Response
import james.ably.assignment.app.dto.UserDTO
import james.ably.assignment.app.service.UserService
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/v1/users")
class UserController (
    private val userService: UserService){

    @GetMapping("/me")
    fun me(): Response<UserDTO> = Response.ok(userService.getCurrentUser())

    @PostMapping("/change/password")
    fun changePassword(@Valid @RequestBody passwordChangeDTO: PasswordChangeDTO): Response<Boolean> {
        userService.passwordChange(passwordChangeDTO)
        return Response.ok(true)
    }
}