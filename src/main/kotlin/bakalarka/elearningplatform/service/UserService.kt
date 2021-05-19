package bakalarka.elearningplatform.service

import bakalarka.elearningplatform.model.ROLES
import bakalarka.elearningplatform.request.UpdateUserRequest
import bakalarka.elearningplatform.security.Management
import com.auth0.client.mgmt.filter.PageFilter
import com.auth0.exception.APIException
import com.auth0.exception.Auth0Exception
import com.auth0.json.mgmt.Role
import com.auth0.json.mgmt.users.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Service
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

    fun addUserRole(userId: String, role: ROLES) {
        val userList = mutableListOf<String>()
        userList.add(userId)
        management.managementApi.roles().assignUsers(role.code, userList).execute()
    }

    fun update(request: UpdateUserRequest): Boolean {
        val (name, surname) = request
        val userId = getUserId()
        val response = management.managementApi.users().listRoles(userId, PageFilter()).execute()
        val data = User()
        data.givenName = name
        data.familyName = surname
        val dataRequest = management.managementApi.users().update(userId, data)
        try {
            if (!response.items.any { role: Role? -> role?.name == "user" }) {
                addUserRole(userId, ROLES.USER)
            }
            dataRequest.execute()
            return true
        } catch (exception: APIException) {
            // api error
            println(exception)
        } catch (exception: Auth0Exception) {
            println(exception)
            // request error
        }
        return false
    }
}
