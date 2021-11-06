package james.ably.assignment.app.exception

import java.lang.Exception

open class IntendedException(
    override val message: String?
): Exception(message)