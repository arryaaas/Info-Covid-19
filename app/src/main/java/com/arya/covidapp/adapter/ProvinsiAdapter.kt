package com.arya.covidapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import androidx.recyclerview.widget.RecyclerView
import com.arya.covidapp.R
import com.arya.covidapp.data.model.DataAttributesProvinsi
import com.arya.covidapp.data.model.DataProvinsi
import kotlinx.android.synthetic.main.item_provinsi.view.*

class ProvinsiAdapter(
    private val list: List<DataProvinsi>?
): RecyclerView.Adapter<ProvinsiAdapter.ViewHolder>() {

    var listFilter: List<DataProvinsi>?

    init {
        listFilter = list
    }

    fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val charString = constraint.toString()
                listFilter = if (constraint.isEmpty()) {
                    list
                } else {
                    val filteredList: ArrayList<DataProvinsi> = ArrayList()
                    if (list != null) {
                        for (item in list) {
                            if (item.attributes?.Provinsi?.toLowerCase()?.contains(charString.toLowerCase())!!) {
                                filteredList.add(item)
                            }
                        }
                    }
                    filteredList
                }
                val filterResult = FilterResults()
                filterResult.values = listFilter
                return  filterResult
            }

            override fun publishResults(constraint: CharSequence, results: FilterResults) {
                listFilter = results.values as List<DataProvinsi>
                notifyDataSetChanged()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                        R.layout.item_provinsi,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        listFilter?.get(position)?.attributes?.let { holder.bindView(it) }
    }

    override fun getItemCount(): Int = listFilter?.size ?: 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(attributes: DataAttributesProvinsi) {
            itemView.tv_1.text = attributes.Provinsi
            itemView.tv_2.text = attributes.Kasus_Posi
            itemView.tv_3.text = attributes.Kasus_Semb
            itemView.tv_4.text = attributes.Kasus_Meni
        }
    }

}