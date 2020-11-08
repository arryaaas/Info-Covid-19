package com.arya.covidapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arya.covidapp.R
import com.arya.covidapp.data.model.ListItem
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class ItemAdapter (
        private val listItem: List<ListItem>,
        private val onItemClick: (list: ListItem) -> Unit
): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                        R.layout.item_informasi,
                    parent,
                    false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(listItem[holder.adapterPosition])

    override fun getItemCount(): Int = listItem.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(list: ListItem) {
            with(itemView) {
                val image: ImageView = itemView.findViewById(R.id.iv_row_image)
                Glide.with(context)
                    .load(list.image)
                    .centerCrop()
                    .into(image)

                val bgImage: CircleImageView = itemView.findViewById(R.id.iv_bg_image)
                bgImage.setImageResource(list.colorBg)

                val title: TextView = itemView.findViewById(R.id.tv_row_name)
                title.text = list.title

                itemView.setOnClickListener {
                    onItemClick.invoke(list)
                }
            }
        }
    }
}
