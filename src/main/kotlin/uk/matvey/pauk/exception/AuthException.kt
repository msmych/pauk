package uk.matvey.pauk.exception

class AuthException(
    message: String? = null,
    cause: Throwable? = null
) : RuntimeException(message, cause)
