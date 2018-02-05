package com.sandra.dupre.mendeleivpower.android.table

import android.os.Handler
import android.os.Looper
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel

class TableViewDecorator: TableView {

    var view: TableView? = null

    override fun displayTable(resumeAtoms: List<ResumeAtomViewModel>) {
        Handler(Looper.getMainLooper()).post { view?.displayTable(resumeAtoms) }
    }
}