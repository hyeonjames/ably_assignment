package james.ably.assignment.app.dto

data class MobileVerifyDTO (
    val forSignUp: Boolean,
    val mobile: String,
    val token: String
        )