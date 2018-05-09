package com.sandra.dupre.mendeleivpower.android.table

import android.os.Handler
import android.os.Looper
import com.sandra.dupre.mendeleivpower.android.viewModel.FamilyViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.TableViewModel

class TableViewDecorator: TableView {

    var view: TableView? = null

    override fun displayTable(tableViewModel: TableViewModel) {
        Handler(Looper.getMainLooper()).post { view?.displayTable(tableViewModel) }
    }

    override fun displayFilter(families: List<FamilyViewModel>) {
        Handler(Looper.getMainLooper()).post { view?.displayFilter(families) }

    }
}