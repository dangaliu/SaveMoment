package com.example.savemoment.presentation.ui.addMoment.view

import android.Manifest
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.savemoment.R
import com.example.savemoment.databinding.ActivityAddMomentBinding
import com.example.savemoment.domain.model.Moment
import com.example.savemoment.presentation.ui.addMoment.viewmodel.AddMomentViewModel
import com.example.savemoment.utils.Constants
import com.example.savemoment.utils.initTempUri
import com.example.savemoment.utils.showToast
import com.example.savemoment.utils.writeImageToInternalStorage
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddMomentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMomentBinding
    private val viewModel by viewModel<AddMomentViewModel>()
    private var isNewImage = false
    private val selectImageFromGalleryLauncher =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                Glide.with(this).load(uri).into(binding.ivAddImage)
                isNewImage = true
            }
        }

    private var cameraPermissionGranted = true

    private var latestTmpUri: Uri? = null

    private val registerTakePictureLauncher =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                latestTmpUri?.let { uri ->
                    Glide.with(this).load(uri).into(binding.ivAddImage)
                    isNewImage = true
                }
            }
        }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            cameraPermissionGranted = isGranted
        }


    override fun onCreate(savedInstanceState: Bundle?) = runBlocking {
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
                binding.toolbar.title = resources.getString(R.string.update_moment_toolbar)
                setOldData(moment)
            }
            return
        }
        binding.toolbar.title = resources.getString(R.string.add_moment_toolbar)
        requestPermissionLauncher.launch(Manifest.permission.CAMERA)
    }

    private fun setListeners() {
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
                                    picture = if (isNewImage) {
                                        isNewImage = false
                                        writeImageToInternalStorage(this, binding.ivAddImage)
                                    } else "null"
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

                R.id.fromGallery -> {
                    selectImageFromGalleryLauncher.launch("image/*")
                    true
                }
                R.id.fromCamera -> {
                    if (cameraPermissionGranted) {
                        takeImage()
                    }
                    true
                }
                else -> false
            }
        }
    }

    private fun takeImage() {
        initTempUri(this).let {
            latestTmpUri = it
            registerTakePictureLauncher.launch(it)
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
            picture = if (isNewImage) {
                isNewImage = false
                writeImageToInternalStorage(this, binding.ivAddImage)
            } else moment.picture
        )
    }

    private fun getOldData(): Moment? {
        return if (intent?.extras?.containsKey(Constants.MOMENT_KEY) == true) {
            intent.extras!!.getParcelable(Constants.MOMENT_KEY)!!
        } else null
    }
}