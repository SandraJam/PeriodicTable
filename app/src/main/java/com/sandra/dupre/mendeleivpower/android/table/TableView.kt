package com.sandra.dupre.mendeleivpower.android.table

import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel

interface TableView {
    fun displayTable(resumeAtoms: List<ResumeAtomViewModel>)
}