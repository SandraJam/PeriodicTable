package com.sandra.dupre.mendeleivpower.android.detail

import com.sandra.dupre.mendeleivpower.android.AtomPresenterHelper
import com.sandra.dupre.mendeleivpower.kernel.DetailPresenter
import com.sandra.dupre.mendeleivpower.kernel.entity.Atom

open class DetailPresenterImpl(
        private val view: DetailView,
        private val helper: AtomPresenterHelper
) : DetailPresenter {
    override fun presentDetail(atom: Atom?) {
        if (atom == null) {
            view.displayError()
        } else {
            view.displayAtom(helper.getAtomViewModel(atom))
        }
    }
}