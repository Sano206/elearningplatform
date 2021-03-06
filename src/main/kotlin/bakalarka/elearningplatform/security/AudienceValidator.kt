package bakalarka.elearningplatform.security

import org.springframework.security.oauth2.core.OAuth2Error
import org.springframework.security.oauth2.core.OAuth2ErrorCodes
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.util.Assert

internal class AudienceValidator(audience: String) : OAuth2TokenValidator<Jwt> {
    private val audience: String
    override fun validate(jwt: Jwt): OAuth2TokenValidatorResult {
        val audiences = jwt.audience
        if (!audiences.contains(audience)) {
            val err = OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN)
            return OAuth2TokenValidatorResult.failure(err)
        }
        return OAuth2TokenValidatorResult.success()
    }

    init {
        Assert.hasText(audience, "audience is null or empty")
        this.audience = audience
    }
}
