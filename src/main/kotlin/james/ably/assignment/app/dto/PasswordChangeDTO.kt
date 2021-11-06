package james.ably.assignment.app.dto

import javax.validation.constraints.Pattern

data class PasswordChangeDTO (
    val oldPassword: String,
    @field:Pattern(regexp = "^.*(?=^.{8,15}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$",
        message = "비밀번호는 숫자, 문자, 특수문자 하나씩 포함하여 8글자 이상으로 설정가능합니다.")
    val newPassword: String
        )