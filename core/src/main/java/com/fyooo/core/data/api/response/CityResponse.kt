package com.fyooo.core.data.api.response

import com.google.gson.annotations.SerializedName

data class CityResponse(

	@field:SerializedName("rajaongkir")
	val rajaongkir: RajaongkirCity
)

data class ResultsItemCity(

	@field:SerializedName("city_name")
	val cityName: String,

	@field:SerializedName("province")
	val province: String,

	@field:SerializedName("province_id")
	val provinceId: String,

	@field:SerializedName("type")
	val type: String,

	@field:SerializedName("postal_code")
	val postalCode: String,

	@field:SerializedName("city_id")
	val cityId: String
)

data class Query(

	@field:SerializedName("province")
	val province: String
)

data class RajaongkirCity(

    @field:SerializedName("query")
	val query: Query,

    @field:SerializedName("results")
	val results: List<ResultsItemCity>,

    @field:SerializedName("status")
	val status: StatusCity
)

data class StatusCity(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("description")
	val description: String
)
