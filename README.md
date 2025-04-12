# QR Code Generator with Logo in Android (Kotlin)

Welcome to the **#CodingWithDev** channel! In this tutorial, you will learn how to generate a QR code with a logo in an Android app using Kotlin. I'll walk you through the process step-by-step to generate QR codes from URLs and add a custom logo to make your QR code stand out.

---

## Tutorial Highlights

This tutorial covers the following aspects:
- **Adding necessary dependencies** for QR code generation using the popular ZXing library.
- **Creating the logic** to generate a QR code from a URL string in Kotlin.
- **Customizing the QR code** by adding a logo in the center using a bitmap overlay.
- **Handling the QR code rendering** with proper scaling and positioning to ensure the logo fits without compromising quality.
- **Displaying the generated QR code** in an `ImageView`.

---

## Steps to Implement

1. **Add Dependencies**
    - To get started, add the ZXing library for QR code generation.

    ```gradle
    implementation 'com.google.zxing:core:3.3.0'
    implementation 'com.journeyapps:zxing-android-embedded:3.6.0'
    ```

2. **Generate QR Code Logic in Kotlin**
    - Use the following Kotlin function to generate a QR code from a URL:

    ```kotlin
    fun generateQRCode(content: String): Bitmap {
        val size = 512 // Size of the generated QR Code
        val qrCodeWriter = QRCodeWriter()
        val bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, size, size)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

        for (x in 0 until width) {
            for (y in 0 until height) {
                bmp.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        return bmp
    }
    ```

3. **Add Logo to QR Code**
    - Overlay your logo on the generated QR code bitmap:

    ```kotlin
    fun addLogoToQRCode(qrCodeBitmap: Bitmap, logo: Bitmap): Bitmap {
        val width = qrCodeBitmap.width
        val height = qrCodeBitmap.height

        val overlayBitmap = qrCodeBitmap.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(overlayBitmap)
        val logoSize = width / 5 // Logo will be 1/5th of QR code size

        val left = (width - logoSize) / 2
        val top = (height - logoSize) / 2
        val logoScaled = Bitmap.createScaledBitmap(logo, logoSize, logoSize, false)

        canvas.drawBitmap(logoScaled, left.toFloat(), top.toFloat(), null)
        return overlayBitmap
    }
    ```

4. **Display the QR Code in ImageView**
    - Finally, display the generated QR code with the logo in an `ImageView`:

    ```kotlin
    val qrCodeBitmap = generateQRCode("https://your-url-here.com")
    val logoBitmap = BitmapFactory.decodeResource(resources, R.drawable.logo)
    val qrCodeWithLogo = addLogoToQRCode(qrCodeBitmap, logoBitmap)
    imageView.setImageBitmap(qrCodeWithLogo)
    ```

---


▶️ **Watch the Full Video Tutorial Here**  
🔗 [https://youtu.be/yuGIo5-8Tlc](https://youtu.be/yuGIo5-8Tlc)


## YouTube Channel

Check out the my **YouTube channel**:
[Watch the video on YouTube](https://www.youtube.com/codingwithdev)

---

## Follow Me
- [Medium](https://medium.com/@devendrac706/)

---

## Technologies Used
- **Kotlin**
- **Android Studio**
- **ZXing Library** for QR Code generation
- **Android ImageView**

---

## License

This project is open-source and available under the [MIT License](LICENSE).

---

Thanks for visiting, and don't forget to **Like**, **Share**, **Comment**, and **Subscribe** to my YouTube channel for more tutorials!

#CodingWithDev #QRCodeGeneration #Kotlin #AndroidDevelopment #Tutorial
