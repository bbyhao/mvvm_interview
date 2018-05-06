package com.byy.mvvm_interviewtest.data

import com.google.gson.annotations.SerializedName




/**
 * @author Created by fq on 2018/4/28.
 */
const val localData:String="{\"__deprecation_message__\":\"This API endpoint is deprecated and will stop working on June 1st, 2018. For more information please visit: https://github.com/fixerAPI/fixer#readme\",\"base\":\"EUR\",\"date\":\"2018-04-27\",\"rates\":{\"AUD\":1.5975,\"BGN\":1.9558,\"BRL\":4.1992,\"CAD\":1.5549,\"CHF\":1.196,\"CNY\":7.6517,\"CZK\":25.471,\"DKK\":7.4501,\"GBP\":0.877,\"HKD\":9.4731,\"HRK\":7.4195,\"HUF\":312.84,\"IDR\":16723.0,\"ILS\":4.3376,\"INR\":80.464,\"ISK\":122.6,\"JPY\":131.95,\"KRW\":1291.8,\"MXN\":22.693,\"MYR\":4.7231,\"NOK\":9.659,\"NZD\":1.7116,\"PHP\":62.392,\"PLN\":4.2161,\"RON\":4.6615,\"RUB\":75.418,\"SEK\":10.518,\"SGD\":1.6005,\"THB\":38.117,\"TRY\":4.8882,\"USD\":1.207,\"ZAR\":14.964}}"

		//This API endpoint is deprecated and will stop working on June 1st, 2018. For more information please visit: https://github.com/fixerAPI/fixer#readme
data class RateEntry(
		@SerializedName("__deprecation_message__") var deprecationMessage: String? = "",
		@SerializedName("base") var base: String? = "", //EUR
		@SerializedName("date") var date: String? = "", //2018-04-27
		@SerializedName("rates") var rates: Rates? = Rates(),
		@SerializedName("result") var result: Double? = 0.0
)

data class Rates(
		@SerializedName("AUD") var aUD: Double? = 0.0, //1.5975
		@SerializedName("BGN") var bGN: Double? = 0.0, //1.9558
		@SerializedName("BRL") var bRL: Double? = 0.0, //4.1992
		@SerializedName("CAD") var cAD: Double? = 0.0, //1.5549
		@SerializedName("CHF") var cHF: Double? = 0.0, //1.196
		@SerializedName("CNY") var cNY: Double? = 0.0, //7.6517
		@SerializedName("CZK") var cZK: Double? = 0.0, //25.471
		@SerializedName("DKK") var dKK: Double? = 0.0, //7.4501
		@SerializedName("GBP") var gBP: Double? = 0.0, //0.877
		@SerializedName("HKD") var hKD: Double? = 0.0, //9.4731
		@SerializedName("HRK") var hRK: Double? = 0.0, //7.4195
		@SerializedName("HUF") var hUF: Double? = 0.0, //312.84
		@SerializedName("IDR") var iDR: Double? = 0.0, //16723.0
		@SerializedName("ILS") var iLS: Double? = 0.0, //4.3376
		@SerializedName("INR") var iNR: Double? = 0.0, //80.464
		@SerializedName("ISK") var iSK: Double? = 0.0, //122.6
		@SerializedName("JPY") var jPY: Double? = 0.0, //131.95
		@SerializedName("KRW") var kRW: Double? = 0.0, //1291.8
		@SerializedName("MXN") var mXN: Double? = 0.0, //22.693
		@SerializedName("MYR") var mYR: Double? = 0.0, //4.7231
		@SerializedName("NOK") var nOK: Double? = 0.0, //9.659
		@SerializedName("NZD") var nZD: Double? = 0.0, //1.7116
		@SerializedName("PHP") var pHP: Double? = 0.0, //62.392
		@SerializedName("PLN") var pLN: Double? = 0.0, //4.2161
		@SerializedName("RON") var rON: Double? = 0.0, //4.6615
		@SerializedName("RUB") var rUB: Double? = 0.0, //75.418
		@SerializedName("SEK") var sEK: Double? = 0.0, //10.518
		@SerializedName("SGD") var sGD: Double? = 0.0, //1.6005
		@SerializedName("THB") var tHB: Double? = 0.0, //38.117
		@SerializedName("TRY") var tRY: Double? = 0.0, //4.8882
		@SerializedName("USD") var uSD: Double? = 0.0, //1.207
		@SerializedName("ZAR") var zAR: Double? = 0.0 //14.964
)
data class RateItem(var country:String="111",
					var data:Double=0.0,
					var result: String="222")
