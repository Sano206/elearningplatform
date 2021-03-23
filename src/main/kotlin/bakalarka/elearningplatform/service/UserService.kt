package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.model.Instructor
import bakalarka.elearningplatform.request.UpdateUserRequest
import bakalarka.elearningplatform.security.Management
import com.auth0.client.mgmt.filter.PageFilter
import com.auth0.exception.APIException
import com.auth0.exception.Auth0Exception
import com.auth0.json.mgmt.users.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
import java.util.*
import javax.naming.AuthenticationException

@Service
class UserService(
        var management: Management
) {

    companion object {
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

    fun update(request: UpdateUserRequest) {
        val (name, surname) = request
        val userId = getUserId()
        val userList = mutableListOf<String>()
        userList.add(userId)
        val roleRequest = management.managementApi.roles().assignUsers("rol_K4GNrsvT2qTOc4Js", userList)
        val data = User()
        data.givenName = name
        data.familyName = surname
        val dataRequest = management.managementApi.users().update(userId, data)
        try {
            roleRequest.execute()
            dataRequest.execute()
        } catch (exception: APIException) {
            // api error
            println(exception)
        } catch (exception: Auth0Exception) {
            println(exception)
            // request error
        }
    }

}