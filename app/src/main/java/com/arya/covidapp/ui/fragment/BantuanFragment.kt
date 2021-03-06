package com.arya.covidapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arya.covidapp.adapter.ItemAdapter
import com.arya.covidapp.data.model.ListItem
import com.arya.covidapp.R
import com.arya.covidapp.ui.activity.WebViewActivity
import kotlinx.android.synthetic.main.fragment_bantuan.*

class BantuanFragment : Fragment() {

    private lateinit var adapter: ItemAdapter
    private lateinit var list: MutableList<ListItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bantuan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()

        sub2.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("title", "Gejala")
            this.startActivity(intent)
        }
    }

    private fun setupRecyclerview() {
        list = ArrayList()
        adapter = ItemAdapter(list) { list -> moveToWebView(list) }
        rv_bantuan.adapter = adapter
        createListData()
    }

    private fun moveToWebView(list: ListItem) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("title", list.title)
        this.startActivity(intent)
    }

    private fun createListData() {
        var listBantuan = ListItem(
            R.drawable.ic_hotline,
            R.color.bgColorHotline,
            "Hotline"
        )
        list.add(listBantuan)

        listBantuan = ListItem(
            R.drawable.ic_konsultasi_dokter,
            R.color.bgColorKonsultasiDokter,
            "Konsultasi Dokter"
        )
        list.add(listBantuan)

        listBantuan = ListItem(
            R.drawable.ic_rumah_sakit,
            R.color.bgColorRumahSakitRujukan,
            "Rumah Sakit Rujukan"
        )
        list.add(listBantuan)

        adapter.notifyDataSetChanged()
    }
}