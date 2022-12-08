package com.example.savemoment.utils

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.example.savemoment.R
import com.example.savemoment.domain.model.Moment
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.util.*

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun writeImageToInternalStorage(
    context: Context,
    imageView: ImageView,
): String {
    val bitmap = (imageView.drawable as BitmapDrawable).bitmap
    val cw = ContextWrapper(context.applicationContext)
    val directory = cw.getDir("images", Context.MODE_PRIVATE)
    val myPath = File(
        directory,
        "moment_${UUID.randomUUID()}"
    )
    var fos: FileOutputStream? = null
    try {
        fos = FileOutputStream(myPath)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
    } catch (e: Exception) {
        context.showToast(e.message.toString())
    } finally {
        fos?.close()
    }
    return myPath.path
}

fun initTempUri(context: Context): Uri {
    val directory = ContextWrapper(context).getDir("images", Context.MODE_PRIVATE)
    val imageFile = File.createTempFile("moment_${UUID.randomUUID()}", ".png", directory)

    return FileProvider.getUriForFile(
        context,
        context.resources.getString(R.string.authorities),
        imageFile
    )
}