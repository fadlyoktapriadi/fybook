package com.fyooo.fybook.data.api.response

import com.google.gson.annotations.SerializedName

data class CostResponse(

	@field:SerializedName("rajaongkir")
	val rajaongkir: RajaongkirCost
)

data class RajaongkirCost(

	@field:SerializedName("query")
	val query: QueryCost,

	@field:SerializedName("destination_details")
	val destinationDetails: DestinationDetails,

	@field:SerializedName("origin_details")
	val originDetails: OriginDetails,

	@field:SerializedName("results")
	val results: List<ResultsItem>,

	@field:SerializedName("status")
	val status: StatusCost
)

data class CostsItem(

	@field:SerializedName("cost")
	val cost: List<CostItem>,

	@field:SerializedName("service")
	val service: String,

	@field:SerializedName("description")
	val description: String
)

data class ResultsItem(

	@field:SerializedName("costs")
	val costs: List<CostsItem>,

	@field:SerializedName("code")
	val code: String,

	@field:SerializedName("name")
	val name: String
)

data class QueryCost(

	@field:SerializedName("courier")
	val courier: String,

	@field:SerializedName("origin")
	val origin: String,

	@field:SerializedName("destination")
	val destination: String,

	@field:SerializedName("weight")
	val weight: Int
)

data class CostItem(

	@field:SerializedName("note")
	val note: String,

	@field:SerializedName("etd")
	val etd: String,

	@field:SerializedName("value")
	val value: Int
)

data class OriginDetails(

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

data class StatusCost(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("description")
	val description: String
)

data class DestinationDetails(

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
