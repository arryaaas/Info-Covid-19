package com.arya.covidapp.data.api

import com.arya.covidapp.data.model.DataGlobal
import com.arya.covidapp.data.model.DataProvinsi
import retrofit2.Call
import retrofit2.http.GET

interface DataSource {
    @GET("indonesia/provinsi")
    fun getProvinsi(): Call<List<DataProvinsi>>

    @GET("/")
    fun getGlobal(): Call<List<DataGlobal>>
}