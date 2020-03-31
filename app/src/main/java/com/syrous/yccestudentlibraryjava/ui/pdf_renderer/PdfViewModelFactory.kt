package com.syrous.yccestudentlibraryjava.ui.pdf_renderer

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.io.File


class PdfViewModelFactory(private val application: Application, private val file: File): ViewModelProvider.Factory {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PdfRendererViewModel::class.java)){
            return PdfRendererViewModel(application, file) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}