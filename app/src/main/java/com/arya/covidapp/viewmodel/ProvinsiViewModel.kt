package com.arya.covidapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arya.covidapp.data.api.DataSource
import com.arya.covidapp.data.api.NetworkProvider
import com.arya.covidapp.data.model.DataProvinsi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProvinsiViewModel : ViewModel() {

    val listProvinsi = MutableLiveData<List<DataProvinsi>>()

    fun setListProvinsi() {
        val dataSource = NetworkProvider.providesHttpAdapter()
            .create(DataSource::class.java)
        dataSource.getProvinsi().enqueue(object : Callback<List<DataProvinsi>> {
            override fun onResponse(
                    call: Call<List<DataProvinsi>>,
                    response: Response<List<DataProvinsi>>
            ) {
                listProvinsi.value = response.body()
            }

            override fun onFailure(call: Call<List<DataProvinsi>>, t: Throwable) {
                Log.e("ProvinsiViewModel", "${t.printStackTrace()}")
            }

        })
    }

    fun getListProvinsi(): LiveData<List<DataProvinsi>> = listProvinsi
}