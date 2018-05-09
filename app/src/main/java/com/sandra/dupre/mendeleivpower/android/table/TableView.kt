package com.sandra.dupre.mendeleivpower.android.table

import com.sandra.dupre.mendeleivpower.android.viewModel.FamilyViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.TableViewModel

interface TableView {
    fun displayTable(tableViewModel: TableViewModel)
    fun displayFilter(families: List<FamilyViewModel>)
}