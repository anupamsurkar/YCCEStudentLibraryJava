
package com.syrous.yccestudentlibraryjava.ui.pdf_renderer

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.syrous.yccestudentlibraryjava.R
import com.syrous.yccestudentlibraryjava.databinding.PdfRendererBasicFragmentBinding
import timber.log.Timber
import java.io.File

/**
 * This fragment has a big [ImageView] that shows PDF pages, and 2 [Button]s to move between pages.
 */

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class FragmentPdfRenderer : Fragment() {

    private lateinit var viewModel: PdfRendererViewModel

    private lateinit var binding: PdfRendererBasicFragmentBinding

    @SuppressLint("BinaryOperationInTimber")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val file = activity?.intent?.getSerializableExtra("PDF_FILE") as File
        Timber.d("File path : ${file.absolutePath}")

       binding = DataBindingUtil.inflate(
                inflater,
                R.layout.pdf_renderer_basic_fragment,
                container,
                false)

        val viewModelFactory = PdfViewModelFactory(requireActivity().application, file)

        viewModel = ViewModelProvider(this, viewModelFactory)
                .get(PdfRendererViewModel::class.java)

        binding.pdfRendererViewModel = viewModel

        binding.lifecycleOwner = this


        viewModel.pageBitmap.observe(viewLifecycleOwner, Observer { bitmap ->
            binding.pdfImageView.setImageBitmap(bitmap)
        })

        viewModel.previousEnabled.observe(viewLifecycleOwner, Observer {
            binding.previous.isEnabled = it
        })

        viewModel.nextEnabled.observe(viewLifecycleOwner, Observer {
            binding.next.isEnabled = it
        })

        return binding.root
    }


}
