package com.syrous.yccestudentlibraryjava.ui.pdf_renderer

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.syrous.yccestudentlibraryjava.ui.SingleFragmentActivity

class ActivityPDFRenderer: SingleFragmentActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun createFragment(): Fragment {
        return FragmentPdfRenderer()
    }
}