package com.example.filesmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBar
import com.example.adapters.DataModels
import kotlinx.android.synthetic.main.activity_add_folder.*
import kotlinx.android.synthetic.main.activity_edit_folder.*
import kotlinx.android.synthetic.main.center_edit_folder_layout.*
import java.io.Serializable

class EditFolderActivity : AppCompatActivity() {
    companion object {
        const val UPDATE_NAME: String = "Update Name"
        const val UPDATE_DES: String = "Update Des"
        const val UPDATE_FOLDER:String = "Update Folder"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_folder)

        var actionBar = supportActionBar
        actionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar.setCustomView(R.layout.center_edit_folder_layout)

        //Receive data from Main
        val receivedFolder =
            intent.getSerializableExtra(MainActivity.EDITED_FOLDER) as? DataModels
        edtTxtEditedFolderName.setText(receivedFolder?.nameFolder)
        edtTxtEditedFolderDes.setText(receivedFolder?.desFolder)

        //Press the Cancel Btn
        imgCancelEditBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //Send data to Main
        imgSaveEditedFolder.setOnClickListener {
            val updateFolderName = edtTxtEditedFolderName.text.toString()
            val updateFolderDes = edtTxtEditedFolderDes.text.toString()
            val updateFolder = DataModels(updateFolderName,updateFolderDes)
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(UPDATE_FOLDER,updateFolder as Serializable)
            startActivity(intent)
        }
    }
}