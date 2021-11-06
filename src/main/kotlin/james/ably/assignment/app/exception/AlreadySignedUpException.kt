package james.ably.assignment.app.exception

class AlreadySignedUpException(
    override val message: String?
): IntendedException(message)