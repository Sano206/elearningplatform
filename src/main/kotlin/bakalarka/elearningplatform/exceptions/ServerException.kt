package bakalarka.elearningplatform.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class ServerException(message: String? = "Something went wrong, try again please.") : RuntimeException(message)
