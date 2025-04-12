package com.dev.qrcodegenerateexample

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder

class MainActivity : AppCompatActivity() {

    private lateinit var qrCodeImageView : ImageView
    private lateinit var generateQRButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        qrCodeImageView = findViewById(R.id.qrCodeImageView)
        generateQRButton = findViewById(R.id.generateQRButton)

        // button click listener
        generateQRButton.setOnClickListener {
            val content = "https://www.youtube.com/CodingwithDev" // your qr content need to add
            try {
                val bitmap = generateQRCode(content)
                val iconBitmap = BitmapFactory.decodeResource(resources, R.drawable.logo)
                val finalBitmap = overlayIconOnQRCode(bitmap, iconBitmap)
                // set the final bitmap in imageView
                qrCodeImageView.setImageBitmap(finalBitmap)

            }catch (e : Exception){
                Toast.makeText(this,"Error generating QR Code : ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun overlayIconOnQRCode(bitmap: Bitmap, iconBitmap: Bitmap): Bitmap {
         // Create a new Bitmap with the some dimensions as the QR Code
        val combinedBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, bitmap.config?: Bitmap.Config.ARGB_8888)
        val canvas = Canvas(combinedBitmap)
        val paint = Paint()
        // Draw the QR code on the canvas
        canvas.drawBitmap(bitmap,0f,0f,paint)
        // Calculate the position to place the icon at the center
        val iconSize = bitmap.width/7   // icon size as a fraction of QR Code width
        val left = (bitmap.width - iconSize) / 2
        val top = (bitmap.height - iconSize) / 2

        // Draw the icon at the center of the QR Code
        val iconRect = Rect(left,top,left+iconSize,top+iconSize)
        canvas.drawBitmap(iconBitmap, null, iconRect, paint)
        return combinedBitmap
    }

    private fun generateQRCode(content: String): Bitmap {
        val writer = MultiFormatWriter()
        val bitMatrix: BitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512,512)
        val barcodeEncoder = BarcodeEncoder()
        return barcodeEncoder.createBitmap(bitMatrix)
    }
}