package com.studiumrogusowe.goparty.authorization;

import com.studiumrogusowe.goparty.authorization.model.AuthLoginBodyObject;
import com.studiumrogusowe.goparty.authorization.model.AuthRefreshBodyObject;
import com.studiumrogusowe.goparty.authorization.model.AuthResponseObject;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by O10 on 16.04.15.
 * zalezne od PAI endpointy oraz odpowiedzi
 */
public interface AuthApi {
    @POST("/token/refresh")
    AuthResponseObject refreshToken(@Body AuthRefreshBodyObject authRefreshBodyObject);

    @POST("/token/login")
    AuthResponseObject getToken(@Body AuthLoginBodyObject authLoginBodyObject);

    @POST("/token/login")
    void getToken(@Body AuthLoginBodyObject authLoginBodyObject, Callback<AuthResponseObject> reponseCallback);

    @POST("/token/register")
    void registerAndGetToken(@Body AuthLoginBodyObject authLoginBodyObject, Callback<AuthResponseObject> reponseCallback);
}
