package com.example.uicomponests

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.extention.showPopup
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : Base() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeSheet(R.id.bottom_sheet)
        getSheet()?.findViewById<Button>(R.id.btn)?.setOnClickListener {
            closeSheet()
        }

        findViewById<FloatingActionButton>(R.id.post).setOnClickListener {
            showPopup(
                R.layout.new_dialog,
                message = "Now you have all the permissions to download this data package,Go Ahead",
                iconId = R.drawable.android,
                onPositiveClick = {
                    Toast.makeText(this, "Im Selected", Toast.LENGTH_SHORT).show()
                },
                onNegativeClick = {},
                dialogWidth = 250,
                dialogColor = R.color.teal_700

            )

        }


    }


}


