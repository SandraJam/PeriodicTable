package com.sandra.dupre.mendeleivpower.android.table

import com.sandra.dupre.mendeleivpower.android.AtomPresenterHelper
import com.sandra.dupre.mendeleivpower.kernel.TablePresenter
import com.sandra.dupre.mendeleivpower.kernel.entity.Atom

class TablePresenterImpl(
        private val view: TableView,
        private val helper: AtomPresenterHelper
): TablePresenter {

    override fun presentTable(atoms: List<Atom>) {
        view.displayTable(atoms.map { helper.getResumeAtomViewModel(it) })
    }
}