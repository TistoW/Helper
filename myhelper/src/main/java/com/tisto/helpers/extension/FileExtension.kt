package com.tisto.helpers.extension

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.net.Uri
import android.os.Environment
import android.view.View
import androidx.fragment.app.Fragment
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

fun File?.toMultipartBody(name: String = "image"): MultipartBody.Part? {
    if (this == null) return null
    val reqFile: RequestBody = this.asRequestBody("image/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(name, this.name, reqFile)
}

fun File?.toAudioMultipartBody(name: String = "file"): MultipartBody.Part? {
    if (this == null) return null
    val reqFile: RequestBody = this.asRequestBody("audio/*".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(name, this.name, reqFile)
}

fun File?.toPdfMultipartBody(name: String = "file"): MultipartBody.Part? {
    if (this == null) return null
    val reqFile: RequestBody = this.asRequestBody("application/pdf".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(name, this.name, reqFile)
}

fun saveViewToFileImage(view: View, file: File): File {
    // Measure the view at its exact dimensions
    val width = view.width
    val height = view.height

    // Create a bitmap backed by an array of pixels
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

    // Associate a canvas with the bitmap
    val canvas = Canvas(bitmap)

    // Draw the view on the canvas
    view.draw(canvas)

    // Now we can save the bitmap to a file
    var outputStream: FileOutputStream? = null
    try {
        outputStream = FileOutputStream(file)
        // Compress the bitmap, write it to the output stream
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        try {
            outputStream?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // Return the file
    return file
}

fun saveViewAsBitmap(view: View): Bitmap {
    // Measure the view at its exact dimensions
    val width = view.width
    val height = view.height

    // Create a bitmap backed by an array of pixels
    val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

    // Associate a canvas with the bitmap
    val canvas = Canvas(bitmap)

    // Draw the view on the canvas
    view.draw(canvas)

    // Return the bitmap
    return bitmap
}

fun saveBitmapToFileExplorer(imageToSave: Bitmap, fileName: String, pathLocation: String? = null) {
    var savePath = getDownloadDirectory() + "/"
    if (pathLocation != null) {
        savePath = pathLocation
    }

    val direct = File(savePath)
    if (!direct.exists()) {
        val directory = File(savePath)
        directory.mkdirs()
    }
    val file = File(savePath, fileName)
    if (file.exists()) {
        file.delete()
    }
    try {
        val out = FileOutputStream(file)
        imageToSave.compress(Bitmap.CompressFormat.PNG, 100, out)
        out.flush()
        out.close()
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
}

fun getExternalStoragePublicDirectory(): String {
    val downloadDirectory =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path
    val folder = File(downloadDirectory)
    if (!folder.mkdirs()) {
        folder.mkdirs()
    }
    return folder.absolutePath
}

fun getDownloadDirectory(): String {
    return getExternalStoragePublicDirectory()
}

fun Activity.saveFile(imageToSave: Bitmap, pathLocation: String, fileName: String) {
    saveBitmapToFileExplorer(imageToSave, pathLocation, fileName)
}

fun File.resizeImageRes(context: Context, newWidth: Int): File {
    // Decode the original bitmap to check its width
    val inputFile = this
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeFile(inputFile.absolutePath, options)
    val originalWidth = options.outWidth

    // If the original image width is greater than newWidth, proceed with resizing
    if (originalWidth > newWidth) {
        // Load the image for real this time
        options.inJustDecodeBounds = false
        val originalBitmap = BitmapFactory.decodeFile(inputFile.absolutePath, options)

        // Calculate the new height to maintain the aspect ratio
        val aspectRatio = originalBitmap.width.toDouble() / originalBitmap.height.toDouble()
        val newHeight = (newWidth / aspectRatio).toInt()

        // Create a new bitmap with the desired dimensions
        val resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true)

        // Save the resized bitmap to a new file
        val resizedFile =
            File(context.getExternalFilesDir(null), "resized_${System.currentTimeMillis()}.jpg")
        FileOutputStream(resizedFile).use { out ->
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        }

        return resizedFile
    } else {
        // Return the original file if its width is less than or equal to newWidth
        return inputFile
    }
}

fun resizeBitmaps(source: Bitmap, maxWidth: Int): Bitmap {
    return try {
        if (source.height >= source.width) {
            if (source.height <= maxWidth) return source //  return jika sudah kecil
            val aspectRatio = source.width.toDouble() / source.height.toDouble()
            val targetWidth = (maxWidth * aspectRatio).toInt()
            val result = Bitmap.createScaledBitmap(source, targetWidth, maxWidth, false)
            result
        } else {
            if (source.width <= maxWidth) return source // return jika sudah kecil
            val aspectRatio = source.height.toDouble() / source.width.toDouble()
            val targetHeight = (maxWidth * aspectRatio).toInt()
            val result = Bitmap.createScaledBitmap(source, maxWidth, targetHeight, false)
            result
        }
    } catch (e: Exception) {
        source
    }
}

fun Activity.uriToFile(uri: Uri): File? {
//    val file = File(cacheDir, "temp_image_file.jpg")
    val file = File(cacheDir, "${currentTime("yyyy_MM_dd_kk_mm_ss_SSS")}_temp.jpg")
    try {
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)
        inputStream?.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
    } catch (e: Exception) {
        logs(e.message)
        return null
    }
    return file
}

fun Fragment.uriToFile(uri: Uri): File? {
    return requireActivity().uriToFile(uri)
}

fun Uri.toFile(context: Activity): File? {
    return context.uriToFile(this)
}

fun Uri.uriToFile(context: Activity): File? {
    return context.uriToFile(this)
}