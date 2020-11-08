package com.arya.covidapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.arya.covidapp.adapter.ProvinsiAdapter
import com.arya.covidapp.viewmodel.ProvinsiViewModel
import com.arya.covidapp.R
import kotlinx.android.synthetic.main.activity_provinsi.*

class ProvinsiActivity : AppCompatActivity() {

    private lateinit var adapter: ProvinsiAdapter
    private lateinit var viewModel: ProvinsiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_provinsi)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = "Data Provinsi"
        }

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(ProvinsiViewModel::class.java)

        viewModel.setListProvinsi()
        viewModel.getListProvinsi().observe(this, { data ->
            adapter = ProvinsiAdapter(data)
            rv_prov.adapter = adapter
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val menuItem = menu?.findItem(R.id.search_menu)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = "Cari Provinsi"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.getFilter().filter(newText)
                return false
            }

        })
        return super.onCreateOptionsMenu(menu)
    }
}