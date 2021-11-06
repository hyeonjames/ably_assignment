package james.ably.assignment.app.exception

import java.lang.Exception

data class PasswordInvalidException(
    override val message: String?
): IntendedException(message)