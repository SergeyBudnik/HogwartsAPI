package com.bdev.hogwarts_api.rest;

import acropollis.municipali.security.client.exceptions.MunicipaliSecurityCredentialsViolationException;
import acropollis.municipali.security.client.exceptions.MunicipaliSecurityNetworkException;
import acropollis.municipali.security.client.user.MunicipaliSecurityUserClient;
import acropollis.municipali.security.client.user.MunicipaliSecurityUserClientImpl;
import acropollis.municipali.security.common.dto.MunicipaliUserCredentials;
import acropollis.municipali.security.common.dto.MunicipaliUserInfo;
import acropollis.municipali.security.common.dto.MunicipaliUserToken;
import com.bdev.hogwarts_api.exceptions.http.HttpCredentialsViolationException;

import static com.bdev.hogwarts_api.properties.PropertiesConfig.config;

public abstract class CommonRest {
    private final MunicipaliSecurityUserClient securityUserClient =
            new MunicipaliSecurityUserClientImpl();

    MunicipaliUserToken login(MunicipaliUserCredentials credentials) {
        try {
            return securityUserClient.login(
                    config.getSecurityServiceRootUrl(),
                    credentials
            );
        } catch (MunicipaliSecurityNetworkException e) {
            throw new RuntimeException(e);
        } catch (MunicipaliSecurityCredentialsViolationException e) {
            throw new HttpCredentialsViolationException("");
        }
    }

    void logoff(String authToken) {
        try {
            securityUserClient.logout(
                    config.getSecurityServiceRootUrl(),
                    authToken
            );
        } catch (MunicipaliSecurityNetworkException e) {
            throw new RuntimeException(e);
        } catch (MunicipaliSecurityCredentialsViolationException e) {
            throw new HttpCredentialsViolationException("");
        }
    }

    MunicipaliUserInfo getUserInfo(String authToken) {
        try {
            return securityUserClient.getUserInfo(
                    config.getSecurityServiceRootUrl(),
                    authToken
            );
        } catch (MunicipaliSecurityNetworkException e) {
            throw new RuntimeException(e);
        } catch (MunicipaliSecurityCredentialsViolationException e) {
            throw new HttpCredentialsViolationException("");
        }
    }
}
