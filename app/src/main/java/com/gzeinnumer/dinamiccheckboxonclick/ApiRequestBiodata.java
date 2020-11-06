package com.gzeinnumer.dinamiccheckboxonclick;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiRequestBiodata {
    @POST("users/new")
    Call<String> createUser(@Body String user);
}
