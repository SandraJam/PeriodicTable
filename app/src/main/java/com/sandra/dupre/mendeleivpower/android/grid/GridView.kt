package com.sandra.dupre.mendeleivpower.android.grid

import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel

interface GridView {
    fun displayGridAtoms(listAtoms: List<ResumeAtomViewModel>)
}