package bakalarka.elearningplatform.security

import com.auth0.client.auth.AuthAPI
import com.auth0.client.mgmt.ManagementAPI
import com.auth0.json.auth.TokenHolder
import com.auth0.net.AuthRequest
import org.jetbrains.kotlin.konan.file.File
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.util.*

@Service
class Management {
    final val managementApi: ManagementAPI
    private val properties = Properties()
    private final val propertiesFile = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "application.properties"
    private val inputStream = FileInputStream(propertiesFile)

    init {
        properties.load(inputStream)
        val authAPI = AuthAPI(properties["auth0.domain"] as String, properties["auth0.clientId"] as String, properties["auth0.clientSecret"] as String)
        val authRequest: AuthRequest = authAPI.requestToken(properties["auth0.audienceProp"] as String)
        val holder: TokenHolder = authRequest.execute()
        this.managementApi = ManagementAPI(properties["auth0.domain"] as String, holder.accessToken)
    }
}
