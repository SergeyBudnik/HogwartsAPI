package com.bdev.hogwarts_api.rest

import acropollis.municipali.security.client.exceptions.MunicipaliSecurityCredentialsViolationException
import acropollis.municipali.security.client.user.MunicipaliSecurityUserClientImpl
import acropollis.municipali.security.common.dto.MunicipaliUserCredentials
import acropollis.municipali.security.common.dto.MunicipaliUserInfo
import acropollis.municipali.security.common.dto.MunicipaliUserToken
import com.bdev.hogwarts_api.exceptions.http.HttpCredentialsViolationException
import com.bdev.hogwarts_api.properties.PropertiesConfig.config

abstract class CommonRest {
    private val securityUserClient = MunicipaliSecurityUserClientImpl()

    internal open fun login(credentials: MunicipaliUserCredentials): MunicipaliUserToken {
        try {
            return securityUserClient.login(
                    config.securityServiceRootUrl,
                    credentials
            )
        } catch (e: MunicipaliSecurityCredentialsViolationException) {
            throw HttpCredentialsViolationException("")
        }
    }

    internal open fun logoff(authToken: String) {
        try {
            securityUserClient.logout(
                    config.securityServiceRootUrl,
                    authToken
            )
        } catch (e: MunicipaliSecurityCredentialsViolationException) {
            throw HttpCredentialsViolationException("")
        }
    }

    internal fun getUserInfo(authToken: String): MunicipaliUserInfo {
        try {
            return securityUserClient.getUserInfo(
                    config.securityServiceRootUrl,
                    authToken
            )
        } catch (e: MunicipaliSecurityCredentialsViolationException) {
            throw HttpCredentialsViolationException("")
        }
    }
}
