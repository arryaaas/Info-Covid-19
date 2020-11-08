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
import kotlinx.android.synthetic.main.fragment_informasi.*

class InformasiFragment : Fragment() {

    private lateinit var adapter: ItemAdapter
    private lateinit var list: MutableList<ListItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_informasi, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()
    }

    private fun setupRecyclerview() {
        list = ArrayList()
        adapter = ItemAdapter(list) { list -> moveToWebView(list) }
        rv_informasi.adapter = adapter
        createListData()
    }

    private fun moveToWebView(list: ListItem) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("title", list.title)
        this.startActivity(intent)
    }

    private fun createListData() {
        var listInformasi = ListItem(
            R.drawable.ic_mengenal,
            R.color.bgColorMengenal,
            "Mengenal"
        )
        list.add(listInformasi)

        listInformasi = ListItem(
            R.drawable.ic_mencegah,
            R.color.bgColorMencegah,
            "Mencegah"
        )
        list.add(listInformasi)

        listInformasi = ListItem(
            R.drawable.ic_mengobati,
            R.color.bgColorMengobati,
            "Mengobati"
        )
        list.add(listInformasi)

        listInformasi = ListItem(
            R.drawable.ic_mengantisipasi,
            R.color.bgColorMengantisipasi,
            "Mengantisipasi"
        )
        list.add(listInformasi)

        adapter.notifyDataSetChanged()
    }
}