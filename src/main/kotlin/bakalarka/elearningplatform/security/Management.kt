package bakalarka.elearningplatform.security

import com.auth0.client.auth.AuthAPI
import com.auth0.client.mgmt.ManagementAPI
import com.auth0.json.auth.TokenHolder
import com.auth0.net.AuthRequest
import org.springframework.stereotype.Service

@Service
class Management(auth0Properties: Auth0Properties) {
    final val managementApi: ManagementAPI

    init {
        val authAPI = AuthAPI(auth0Properties.domain, auth0Properties.clientId, auth0Properties.clientSecret)
        val authRequest: AuthRequest = authAPI.requestToken(auth0Properties.audienceProp)
        val holder: TokenHolder = authRequest.execute()
        this.managementApi = ManagementAPI(auth0Properties.domain, holder.accessToken)
    }
}
