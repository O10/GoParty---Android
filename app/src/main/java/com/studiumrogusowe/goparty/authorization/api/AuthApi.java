package com.studiumrogusowe.goparty.authorization.api;

import com.studiumrogusowe.goparty.authorization.api.model.AuthLoginBodyObject;
import com.studiumrogusowe.goparty.authorization.api.model.AuthRefreshBodyObject;
import com.studiumrogusowe.goparty.authorization.api.model.AuthResponseObject;

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

    @POST("/auth/signin/credentials")
    AuthResponseObject getToken(@Body AuthLoginBodyObject authLoginBodyObject);

    @POST("/token/login")
    void getToken(@Body AuthLoginBodyObject authLoginBodyObject, Callback<AuthResponseObject> reponseCallback);

    @POST("/auth/signup")
    void registerAndGetToken(@Body AuthLoginBodyObject authLoginBodyObject, Callback<AuthResponseObject> reponseCallback);
}
