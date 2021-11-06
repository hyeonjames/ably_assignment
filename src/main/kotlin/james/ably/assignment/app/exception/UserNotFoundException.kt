package james.ably.assignment.app.exception

data class UserNotFoundException(
    override val message: String?
): IntendedException(message)