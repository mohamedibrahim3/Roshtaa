package com.example.test_roshta


import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.test_roshta.databinding.ActivityMainBinding
import com.example.test_roshta.databinding.BottomsheetlayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding?.addRoshtaPhoto?.setOnClickListener {
            val view: View = layoutInflater.inflate(R.layout.bottomsheetlayout, null)
            val dialog = BottomSheetDialog(this)
            val gallery: ImageView = view.findViewById(R.id.galleryId)
            var camera: ImageView = view.findViewById(R.id.photoId)
            gallery.setOnClickListener {
                pickPhotoGallery()
                runBlocking {
                    delay(3000)
                }
                binding?.txtChoosePhoto?.visibility = View.VISIBLE
                binding?.addRoshtaPhoto?.visibility = View.INVISIBLE
                binding?.btnNext?.text = "أعرف أقرب صيدلية"
                binding?.clearBtnId?.visibility = view.visibility
                binding?.takephoto?.visibility = View.VISIBLE
                binding?.clearBtnId?.setOnClickListener {
                    binding?.txtChoosePhoto?.visibility = View.GONE
                    binding?.takephoto?.visibility = View.GONE
                    binding?.addRoshtaPhoto?.visibility = View.VISIBLE
                    binding?.clearBtnId?.visibility = View.GONE
                }
            }
            camera.setOnClickListener {
                openCamera()
                runBlocking {
                    delay(3000)
                }
                binding?.txtChoosePhoto?.visibility = View.VISIBLE
                binding?.addRoshtaPhoto?.visibility = View.INVISIBLE
                binding?.btnNext?.text = "أعرف أقرب صيدلية"
                binding?.clearBtnId?.visibility = view.visibility
                binding?.takephoto?.visibility = View.VISIBLE
                binding?.clearBtnId?.setOnClickListener {
                    binding?.txtChoosePhoto?.visibility = View.GONE
                    binding?.takephoto?.visibility = View.GONE
                    binding?.addRoshtaPhoto?.visibility = View.VISIBLE
                    binding?.clearBtnId?.visibility = View.GONE
                }
            }
            dialog.setContentView(view)
            dialog.show()
        }
        setContentView(binding?.root)
    }

    //for gallery
    private fun pickPhotoGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 100)

    }
    //for camera
    private lateinit var imageUri: Uri
    private fun openCamera() {
        val values = ContentValues().apply {
            put(MediaStore.Images.Media.TITLE, "New Picture")
            put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        }

        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values)!!
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        }

        startActivityForResult(cameraIntent, 0)
    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            val imageUri = data?.data
            binding?.takephoto?.setImageURI(data?.data)

        }
        else if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            binding?.takephoto?.setImageURI(imageUri)
        }

    }


}