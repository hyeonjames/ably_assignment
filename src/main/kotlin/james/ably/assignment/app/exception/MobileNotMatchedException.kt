package james.ably.assignment.app.exception

data class MobileNotMatchedException(
    override val message: String?
): IntendedException(message)