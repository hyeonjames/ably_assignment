package james.ably.assignment.app.dto

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size

class SignUpDTO(
    @field:Email(message = "유효하지 않은 이메일입니다.")
    @field:NotBlank(message = "이메일을 입력해주세요.")
    val email: String,
    @field:Length(min = 1, max = 50, message = "닉네임은 1~50 글자로 설정가능합니다.")
    val nickName: String,
    @field:Length(min = 1, max = 50, message = "이름은 1~50 글자로 설정가능합니다.")
    val name: String,
    // 8자리 숫자, 문자, 특수문자 포함
    @field:Pattern(regexp = "^.*(?=^.{8,15}\$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#\$%^&+=]).*\$",
        message = "비밀번호는 숫자, 문자, 특수문자 하나씩 포함하여 8글자 이상으로 설정가능합니다.")
    val password: String,
    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$", message = "올바르지 않은 전화번호 입니다. 하이픈을 포함하여 설정해주세요.")
    val mobile: String
)