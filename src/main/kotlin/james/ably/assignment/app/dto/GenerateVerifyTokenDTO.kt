package james.ably.assignment.app.dto

import javax.validation.constraints.Pattern

data class GenerateVerifyTokenDTO (
    @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$", message = "올바르지 않은 전화번호 입니다. 하이픈을 포함하여 설정해주세요.")
    val mobile: String
    )