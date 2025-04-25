package com.fyooo.fybook.data.api.response

import com.google.gson.annotations.SerializedName

data class ProvinceResponse(

	@field:SerializedName("rajaongkir")
	val rajaongkir: Rajaongkir
)

data class ResultsItemProvince(

	@field:SerializedName("province")
	val province: String,

	@field:SerializedName("province_id")
	val provinceId: String
)

data class Rajaongkir(

	@field:SerializedName("query")
	val query: List<Any>,

	@field:SerializedName("results")
	val results: List<ResultsItemProvince>,

	@field:SerializedName("status")
	val status: Status
)

data class Status(

	@field:SerializedName("code")
	val code: Int,

	@field:SerializedName("description")
	val description: String
)
