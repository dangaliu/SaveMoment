package com.example.savemoment.presentation.ui.addMoment.view

import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.savemoment.R
import com.example.savemoment.databinding.ActivityAddMomentBinding
import com.example.savemoment.domain.model.Moment
import com.example.savemoment.presentation.ui.addMoment.viewmodel.AddMomentViewModel
import com.example.savemoment.utils.Constants
import com.example.savemoment.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddMomentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMomentBinding
    private val viewModel by viewModel<AddMomentViewModel>()
    private val selectImageFromGallery = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            binding.ivAddImage.setImageURI(it)
            imageUri = it
        }
    }
    private var imageUri: Uri? = null

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
                                    picture = imageUri.toString()
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
        Glide.with(binding.ivAddImage).load(moment.picture).into(binding.ivAddImage)
    }

    private fun getNewMoment(moment: Moment): Moment {
        return Moment(
            id = moment.id,
            title = binding.etTitle.text.toString(),
            description = binding.etDescription.text.toString(),
            picture = imageUri.toString()
        )
    }

    private fun getOldData(): Moment? {
        return if (intent?.extras?.containsKey(Constants.MOMENT_KEY) == true) {
            intent.extras!!.getParcelable(Constants.MOMENT_KEY)!!
        } else null
    }
}