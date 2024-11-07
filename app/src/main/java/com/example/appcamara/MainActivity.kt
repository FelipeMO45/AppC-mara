package com.example.appcamara

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class MainActivity : AppCompatActivity() {
    private lateinit var imageView:ImageView
    private val REQUEST_INASE_CAPTURE=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets


        }

        var boton_Take_Picture=findViewById<Button>(R.id.buttonTakePhoto)
        imageview=findViewById(R.id.myImageView)
        boton_Take_Picture.setOnClickListener{
            dispatchTakePicture()
        }
        private fun dispatchTakePicture(){

            val takePictureIntent=Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                startActivityForResult(takePictureIntent,REQUEST_INASE_CAPTURE)
            }catch (e:ActivityNotFoundException){
                Toast.makeText(this,"Error en cámara: "+e.localizedMessage,Toast.LENGTH_SHORT).show()
            }

        }
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode, data)
            if(requestCode==REQUEST_INASE_CAPTURE && resultCode== RESULT_OK){
        val imageBitmap=data?.extras?.get("data") as Bitmap
                imageview.setImageBitmap(imageBitmap)


    }
}