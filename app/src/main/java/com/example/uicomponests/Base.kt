package com.example.uicomponests

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

open class Base : AppCompatActivity() {
    private var bottomSheet: ConstraintLayout? = null
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun initializeSheet(@IdRes rootId: Int) {
        try {
            bottomSheet = findViewById(rootId)
            bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)
        } catch (e: Exception) {
            Toast.makeText(this, "Unable to find Sheet ${e.message.toString()}", Toast.LENGTH_SHORT)
                .show()
        }

    }

    fun closeSheet() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    fun expandSheet() {
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    fun getSheet(): ConstraintLayout? {
        return bottomSheet
    }
}