package com.sandra.dupre.mendeleivpower.android.grid

import com.sandra.dupre.mendeleivpower.android.AtomPresenterHelper
import com.sandra.dupre.mendeleivpower.kernel.entity.Atom
import com.sandra.dupre.mendeleivpower.kernel.grid.GridPresenter

class GridPresenterImpl(
        private val view: GridView,
        private val helper: AtomPresenterHelper) : GridPresenter {

    override fun presentGridAtoms(listAtoms: List<Atom>) {
        view.displayGridAtoms(listAtoms.map { helper.getResumeAtomViewModel(it) })
    }
}