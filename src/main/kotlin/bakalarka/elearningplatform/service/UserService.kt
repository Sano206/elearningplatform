package bakalarka.elearningplatform.service

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import java.util.*
import javax.naming.AuthenticationException

class UserService {

    companion object{
        fun getUserId(): String {
            val authentication = SecurityContextHolder.getContext().authentication
            val principal = authentication.credentials as Jwt
            val claims = principal.claims
            if (!claims.containsKey("sub")) {
                throw AuthenticationException()
            }

            return claims["sub"] as String
    }
    }
}