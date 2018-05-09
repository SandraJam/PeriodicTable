package com.sandra.dupre.mendeleivpower.android.grid

import android.os.Handler
import android.os.Looper
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel

class GridViewDecorate: GridView {

    var view: GridView? = null

    override fun displayGridAtoms(listAtoms: List<ResumeAtomViewModel>) {
        Handler(Looper.getMainLooper()).post { view?.displayGridAtoms(listAtoms) }
    }
}