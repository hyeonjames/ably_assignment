package james.ably.assignment.app.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class PasswordChangeDTO (
    @field:Email(message = "유효하지 않은 이메일입니다.")
    @field:NotBlank(message = "이메일을 입력해주세요.")
    val email: String,
    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$", message = "올바르지 않은 전화번호 입니다. 하이픈을 포함하여 설정해주세요.")
    val mobile: String,
    @field:Pattern(regexp = "^.*(?=^.{8,15}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$",
        message = "비밀번호는 숫자, 문자, 특수문자 하나씩 포함하여 8글자 이상으로 설정가능합니다.")
    val newPassword: String
        )