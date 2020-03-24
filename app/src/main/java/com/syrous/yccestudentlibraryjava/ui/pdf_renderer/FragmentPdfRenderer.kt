package com.syrous.yccestudentlibraryjava.ui.pdf_renderer

import android.app.Application
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.Build
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.annotation.WorkerThread
import androidx.fragment.app.Fragment
import com.syrous.yccestudentlibraryjava.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.util.concurrent.Executor
import java.util.concurrent.Executors
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
open class FragmentPdfRenderer(val application: Application, val useInstantExecutor: Boolean): Fragment() {

    private var executor: Executor
    private lateinit var fileDescriptor: ParcelFileDescriptor
    private lateinit var pdfRenderer: PdfRenderer
    private lateinit var currentPage: PdfRenderer.Page
    private var cleared: Boolean? = false

    companion object{

        const val TAG = "FragmentPdfRenderer"
//
//        fun newInstance(): FragmentPdfRenderer {
//            return FragmentPdfRenderer()
//        }
    }

    init {
        if(useInstantExecutor) {
//            executor = Runnable::run
            executor = Executors.newSingleThreadExecutor()
        }
        else {
            executor = Executors.newSingleThreadExecutor()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pdf_renderer_basic_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    fun showPrevious() {

        val index: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            currentPage.index
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }
        if (index > 0) {
            executor.execute(Runnable { showPage(index - 1) })
        }
    }

    fun showNext() {

        val index: Int = currentPage.index
        if (index + 1 < pdfRenderer.pageCount) {
            executor.execute(Runnable { showPage(index + 1) })
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        executor.execute(Runnable {
            try {
                closePdfRenderer()
                cleared = true
            } catch (e: IOException) {
                Log.i(TAG, "Failed to close PdfRenderer", e)
            }
        })
    }

    @WorkerThread
    @Throws(IOException::class)
    private fun openPdfRenderer() {
//        val file = File(getApplication<Application>().getCacheDir(), com.example.android.pdfrendererbasic.PdfRendererBasicViewModel.FILENAME)
//        if (!file.exists()) {
//            // Since PdfRenderer cannot handle the compressed asset file directly, we copy it into
//            // the cache directory.
//            val asset: InputStream = getApplication<Application>().getAssets().open(com.example.android.pdfrendererbasic.PdfRendererBasicViewModel.FILENAME)
//            val output = FileOutputStream(file)
            val buffer = ByteArray(1024)
            var size: Int
//            while (asset.read(buffer).also { size = it } != -1) {
//                output.write(buffer, 0, size)
//            }
//            asset.close()
//            output.close()
//        }
//        fileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY)
//        pdfRenderer = PdfRenderer(fileDescriptor)
    }


    @WorkerThread
    @Throws(IOException::class)
    private fun closePdfRenderer() {
        currentPage.close()
        pdfRenderer.close()
        fileDescriptor.close()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @WorkerThread
    private fun showPage(index: Int) {
        // Make sure to close the current page before opening another one.
        currentPage.close()
        // Use `openPage` to open a specific page in PDF.
        currentPage = pdfRenderer.openPage(index)
        // Important: the destination bitmap must be ARGB (not RGB).
        val bitmap = Bitmap.createBitmap(currentPage.width, currentPage.height,
                Bitmap.Config.ARGB_8888)
        // Here, we render the page onto the Bitmap.
        // To render a portion of the page, use the second and third parameter. Pass nulls to get
        // the default result.
        // Pass either RENDER_MODE_FOR_DISPLAY or RENDER_MODE_FOR_PRINT for the last parameter.
        currentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
//        mPageBitmap.postValue(bitmap)
        val count: Int = pdfRenderer.pageCount
//        mPageInfo.postValue(PdfDocument.PageInfo(index, count))
//        mPreviousEnabled.postValue(index > 0)
//        mNextEnabled.postValue(index + 1 < count)
    }
}