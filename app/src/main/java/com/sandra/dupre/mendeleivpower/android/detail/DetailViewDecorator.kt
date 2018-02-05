package com.sandra.dupre.mendeleivpower.android.detail

import android.os.Handler
import android.os.Looper
import com.sandra.dupre.mendeleivpower.android.viewModel.AtomViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel

class DetailViewDecorator : DetailView {
    var view: DetailView? = null

    override fun displayAtom(viewModel: AtomViewModel) {
        Handler(Looper.getMainLooper()).post { view?.displayAtom(viewModel) }
    }

    override fun displayError() {
        Handler(Looper.getMainLooper()).post { view?.displayError() }
    }
}