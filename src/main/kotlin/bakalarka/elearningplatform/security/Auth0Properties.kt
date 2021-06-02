package bakalarka.elearningplatform.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "auth0")
data class Auth0Properties(
    var clientId: String = "",
    var audience: String = "",
    var domain: String = "",
    var clientSecret: String = "",
    var audienceProp: String = ""
)
