package com.example.savemoment.presentation.ui.addMoment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.savemoment.R
import com.example.savemoment.databinding.ActivityAddMomentBinding
import com.example.savemoment.domain.model.Moment
import com.example.savemoment.presentation.ui.addMoment.viewmodel.AddMomentViewModel
import com.example.savemoment.utils.Constants
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddMomentActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMomentBinding
    private val viewModel by viewModel<AddMomentViewModel>()

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
        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.toolbar.setOnMenuItemClickListener {
            return@setOnMenuItemClickListener when (it.itemId) {
                R.id.done -> {
                    getOldData()?.let { moment ->
                        viewModel.update(getNewMoment(moment))
                        finish()
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
    }

    private fun getNewMoment(moment: Moment): Moment {
        return Moment(
            id = moment.id,
            title = binding.etTitle.text.toString(),
            description = binding.etDescription.text.toString(),
            pictureUri = ""
        )
    }

    private fun getOldData(): Moment? {
        return if (intent?.extras?.containsKey(Constants.MOMENT_KEY) == true) {
            intent.extras!!.getParcelable(Constants.MOMENT_KEY)!!
        } else null
    }
}