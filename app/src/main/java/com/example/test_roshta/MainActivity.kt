package com.example.test_roshta


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.test_roshta.databinding.ActivityMainBinding
import com.example.test_roshta.databinding.BottomsheetlayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.addRoshtaPhoto?.setOnClickListener {
            val view: View = layoutInflater.inflate(R.layout.bottomsheetlayout,null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(view)
            dialog.show()
        }
        setContentView(binding?.root)
    }

}