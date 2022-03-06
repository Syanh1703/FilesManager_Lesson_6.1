package com.example.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filesmanager.R
import kotlinx.android.synthetic.main.item_folder.view.*

class ItemAdapters(val context: Context, val items:ArrayList<DataModels>)
    :RecyclerView.Adapter<ItemAdapters.ViewHolder>(){

    private lateinit var mListener :onItemClickListener

    interface onItemClickListener
    {
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener)
    {
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemAdapters.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_folder, parent, false)
        return ViewHolder(itemView, mListener)
    }

    override fun onBindViewHolder(holder: ItemAdapters.ViewHolder, position: Int) {
        val item = items[position]
        if(holder is ViewHolder)
        {
            holder.folderName.text = item.nameFolder
            holder.describeFolder.text = item.desFolder
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(view :View, listener: onItemClickListener):RecyclerView.ViewHolder(view)
    {
        val folderName :TextView = view.txtViewFolderName
        val describeFolder :TextView = view.txtViewFolderDescription
        init {
            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}
