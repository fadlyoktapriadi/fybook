package com.fyooo.fybook.data.api

import com.fyooo.fybook.data.api.response.CityResponse
import com.fyooo.fybook.data.api.response.CostResponse
import com.fyooo.fybook.data.api.response.ProvinceResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {

    @GET("province")
    suspend fun getProvinces(): ProvinceResponse

    @GET("city")
    suspend fun getCities(
        @Query("province") provinceId: String
    ): CityResponse

    @FormUrlEncoded
    @POST("cost")
    suspend fun getShippingCost(
        @Field("origin") origin: String,
        @Field("destination") destination: String,
        @Field("weight") weight: Int,
        @Field("courier") courier: String
    ): CostResponse
}