package com.arya.covidapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.arya.covidapp.viewmodel.GlobalViewModel
import com.arya.covidapp.R
import com.arya.covidapp.ui.activity.ProvinsiActivity
import com.arya.covidapp.ui.activity.WebViewActivity
import kotlinx.android.synthetic.main.fragment_kasus.*
import java.text.DateFormat
import java.util.*


class KasusFragment : Fragment() {

    private var value: String = ""
    private lateinit var viewModel: GlobalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kasus, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())
            .get(GlobalViewModel::class.java)

        viewModel.setListGlobal()
        viewModel.getListGlobal().observe(viewLifecycleOwner, { data ->
            val calendar = Calendar.getInstance()
            val currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)

            val country = arrayOfNulls<String>(data.size)
            for (i in data.indices) {
                country[i] = data[i].attributes?.Country_Region
            }
            val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_layout, R.id.textView, country)
            spinner.adapter = arrayAdapter

            val defaultCountry = "Indonesia"
            val position = arrayAdapter.getPosition(defaultCountry)
            spinner.setSelection(position)

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    value = data[position].attributes?.Country_Region.toString()
                    tv_sehat.text = data[position].attributes?.Recovered.toString()
                    tv_kasus_positif.text = data[position].attributes?.Confirmed.toString()
                    tv_meninggal.text = data[position].attributes?.Deaths.toString()
                    tv_last_update.text = currentDate
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        })

        tv_lihat_detail.setOnClickListener {
            if (value == "Indonesia") {
                val intent = Intent(context, ProvinsiActivity::class.java)
                startActivity(intent)
            }  else {
                Toast.makeText(context, "Saat ini hanya tersedia untuk indonesia", Toast.LENGTH_SHORT).show()
            }
        }

        btn_berita.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("title", "Berita Penting")
            this.startActivity(intent)
        }
    }
}