package com.inyongtisto.helper.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.inyongtisto.helper.util.AppProgressDialog

abstract class BaseFragment: Fragment() {

    lateinit var progress : AppProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupProgress()
    }

    private fun setupProgress() {
        progress = AppProgressDialog(requireContext())
        progress.setCancelable(false)
        progress.setCanceledOnTouchOutside(false)
    }

}