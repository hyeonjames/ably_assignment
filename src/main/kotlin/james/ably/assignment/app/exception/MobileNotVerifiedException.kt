package james.ably.assignment.app.exception

data class MobileNotVerifiedException (
    override val message: String?
): IntendedException(message)