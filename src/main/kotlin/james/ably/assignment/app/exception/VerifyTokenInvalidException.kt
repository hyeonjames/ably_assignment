package james.ably.assignment.app.exception

data class VerifyTokenInvalidException (
    override val message: String?
    ): IntendedException(message)