package com.example.filesmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.adapters.DataModels
import kotlinx.android.synthetic.main.activity_add_folder.*
import kotlinx.android.synthetic.main.center_add_folder.*
import java.io.Serializable

class AddFolder : AppCompatActivity() {
    companion object {
        const val RETURN_FOLDER: String = "Return Folder"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_folder)

        var actionBar = supportActionBar
        actionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar.setCustomView(R.layout.center_add_folder)

        //Press the Cancel Btn
        imgCancelAddBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //Send data to Main
        imgSaveCreatedBtn.setOnClickListener {
            val createdFolderName = edtTxtFolderName.text.toString()
            val createdFolderDes = edtTxtFolderDes.text.toString()
            val addedDataModels = DataModels(createdFolderName,createdFolderDes)
            val intent2 = Intent(this, MainActivity::class.java)
            intent2.putExtra(RETURN_FOLDER,addedDataModels as Serializable)
            startActivity(intent2)
        }
    }
}