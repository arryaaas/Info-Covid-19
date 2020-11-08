package com.arya.covidapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.arya.covidapp.data.api.DataSource
import com.arya.covidapp.data.api.NetworkProvider
import com.arya.covidapp.data.model.DataGlobal
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlobalViewModel : ViewModel() {

    val listGlobal = MutableLiveData<List<DataGlobal>>()

    fun setListGlobal() {
        val dataSource = NetworkProvider.providesHttpAdapter()
            .create(DataSource::class.java)
        dataSource.getGlobal().enqueue(object : Callback<List<DataGlobal>> {
            override fun onResponse(
                    call: Call<List<DataGlobal>>,
                    response: Response<List<DataGlobal>>
            ) {
                listGlobal.value = response.body()
            }

            override fun onFailure(call: Call<List<DataGlobal>>, t: Throwable) {
                Log.e("GlobalViewModel", "${t.printStackTrace()}")
            }

        })
    }

    fun getListGlobal(): LiveData<List<DataGlobal>> = listGlobal
}