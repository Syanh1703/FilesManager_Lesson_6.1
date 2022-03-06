package com.example.filesmanager

import android.content.ClipData
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adapters.DataModels
import com.example.adapters.ItemAdapters
import kotlinx.android.synthetic.main.activity_edit_folder.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_folder.*
import org.w3c.dom.Text
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var itemList: ArrayList<DataModels>
    private lateinit var itemAdapters: ItemAdapters

    companion object {
        const val TONG_HOP: String = "Tổng hợp tin tức thời sự nóng hổi nhất, của tất cả các ..."
        const val EDITED_FOLDER: String = "Edited Folder"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Move the app name to center
        var actionBar = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        actionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar!!.setCustomView(R.layout.center_app_name)

        itemList = ArrayList()
        itemAdapters = ItemAdapters(this, getItemList())
        rvItemList.layoutManager = LinearLayoutManager(this)
        rvItemList.adapter = itemAdapters

        itemAdapters.setOnItemClickListener(object : ItemAdapters.onItemClickListener {
            override fun onItemClick(position: Int) {//Send to Edit Page
                val transferFolderName = itemList[position].nameFolder
                val transferFolderDes = itemList[position].desFolder
                val dataModels = DataModels(transferFolderName, transferFolderDes)
                val intent = Intent(this@MainActivity, EditFolderActivity::class.java)
                intent.putExtra(EDITED_FOLDER, dataModels as Serializable)
                startActivity(intent)
                Toast.makeText(
                    this@MainActivity,
                    "You just click on item number ${position + 1}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.folder_mamager_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
            R.id.addFolder ->
            {
                val intent = Intent(this, AddFolder::class.java)
                startActivity(intent)
                true
            }
        else ->
        {
            super.onOptionsItemSelected(item)
        }
    }



    private fun getItemList():ArrayList<DataModels>
    {
        itemList.add(DataModels("Tổng hợp tin tức thời sự", TONG_HOP))
        itemList.add(DataModels("Do It Your Self", "Sơn tùng MTP quá đẹp trai hát hay"))
        itemList.add(DataModels("Cảm hứng sáng tạo", TONG_HOP))
        itemList.add(DataModels("Tổng hợp tin tức thời sự", TONG_HOP))
        itemList.add(DataModels("Do It Your Self", TONG_HOP))
        itemList.add(DataModels("Cảm hứng sáng tạo", TONG_HOP))
        return itemList
    }
}