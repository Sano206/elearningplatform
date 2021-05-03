package bakalarka.elearningplatform.security

import com.auth0.client.auth.AuthAPI
import com.auth0.client.mgmt.ManagementAPI
import com.auth0.json.auth.TokenHolder
import com.auth0.net.AuthRequest
import org.springframework.stereotype.Service

@Service
class Management(
    var authAPI: AuthAPI = AuthAPI("gajd-s.eu.auth0.com", "4cZm8ItqsqUKeLluRAbyo67NVePOPzFe", "NE5o5_PMagv_li7JRhucz2ceYGzKYQBqqJv-SpnPVY8yOpBAz6kQkP9qgqKKLdR5"),
    var authRequest: AuthRequest = authAPI.requestToken("https://gajd-s.eu.auth0.com/api/v2/"),
    var holder: TokenHolder = authRequest.execute(),
    var managementApi: ManagementAPI = ManagementAPI("gajd-s.eu.auth0.com", holder.accessToken)
)
