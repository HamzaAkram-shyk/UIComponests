package com.example.uicomponests

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
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
            expandSheet()
        }
    }

}


