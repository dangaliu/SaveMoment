package com.example.savemoment.presentation.ui.addMoment.view

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toFile
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.savemoment.R
import com.example.savemoment.databinding.ActivityAddMomentBinding
import com.example.savemoment.domain.model.Moment
import com.example.savemoment.presentation.ui.addMoment.viewmodel.AddMomentViewModel
import com.example.savemoment.utils.Constants
import com.example.savemoment.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

class AddMomentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMomentBinding
    private val viewModel by viewModel<AddMomentViewModel>()
    private val selectImageFromGallery =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                binding.ivAddImage.setImageURI(it)
            }
        }

    private fun writeImageToInternalStorage(): Uri {
        val bitmap = (binding.ivAddImage.drawable as BitmapDrawable).bitmap
        val cw = ContextWrapper(applicationContext)
        val directory = cw.getDir("images", Context.MODE_PRIVATE)
        val myPath = File(
            directory,
            if (getOldData() != null) "moment_${UUID.randomUUID()}" else "moment_${UUID.randomUUID()}"
        )
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(myPath)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
        } catch (e: Exception) {
            showToast(e.message.toString())
        } finally {
            fos?.close()
        }
        return myPath.toUri()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMomentBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        setListeners()
    }

    private fun init() {
        intent.extras?.let {
            val moment: Moment? = it.getParcelable(Constants.MOMENT_KEY)
            if (it.containsKey(Constants.MOMENT_KEY) && moment != null) {
                setOldData(moment)
            }
        }
    }

    private fun setListeners() {
        binding.ivAddImage.setOnClickListener {
            selectImageFromGallery.launch("image/*")
        }
        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.toolbar.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.done -> {
                    val title = binding.etTitle.text.toString()
                    val description = binding.etDescription.text.toString()
                    if (intent.extras?.containsKey(Constants.MOMENT_KEY) != true) {
                        if (title.isBlank()) {
                            showToast("Название не должно быть пустым")
                        } else {
                            viewModel.save(
                                Moment(
                                    title = title,
                                    description = description,
                                    picture = writeImageToInternalStorage().toString()
                                )
                            )
                            finish()
                        }
                    } else {
                        getOldData()?.let { moment ->
                            if (title.isBlank()) {
                                showToast("Название не должно быть пустым")
                            } else {
                                viewModel.update(getNewMoment(moment))
                                finish()
                            }
                        }
                    }
                    true
                }
                else -> false
            }
        }
    }


    private fun setOldData(moment: Moment) {
        binding.etTitle.setText(moment.title)
        binding.etDescription.setText(moment.description)
        if (moment.picture.toString() != "null") {
            Glide.with(binding.ivAddImage).load(moment.picture).into(binding.ivAddImage)
        }
    }

    private fun getNewMoment(moment: Moment): Moment {
        return Moment(
            id = moment.id,
            title = binding.etTitle.text.toString(),
            description = binding.etDescription.text.toString(),
            picture = writeImageToInternalStorage().toString()
        )
    }

    private fun getOldData(): Moment? {
        return if (intent?.extras?.containsKey(Constants.MOMENT_KEY) == true) {
            intent.extras!!.getParcelable(Constants.MOMENT_KEY)!!
        } else null
    }
}