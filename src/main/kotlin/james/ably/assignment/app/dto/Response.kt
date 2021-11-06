package james.ably.assignment.app.dto

class Response<T>(
    val ok: Boolean,
    val data: T?,
    val error: String?
) {
    companion object {
        fun <T> ok(data: T?) = Response(true, data, null)
        fun <T> fail(data: T? = null, error: String?) = Response(false, data, error)
    }
}
