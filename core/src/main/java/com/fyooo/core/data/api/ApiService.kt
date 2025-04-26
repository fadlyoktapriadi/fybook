package com.fyooo.core.data.api


import com.fyooo.core.data.api.response.CostResponse
import com.fyooo.core.data.api.response.ProvinceResponse
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
    ): com.fyooo.core.data.api.response.CityResponse

    @FormUrlEncoded
    @POST("cost")
    suspend fun getShippingCost(
        @Field("origin") origin: String,
        @Field("destination") destination: String,
        @Field("weight") weight: Int,
        @Field("courier") courier: String
    ): CostResponse
}