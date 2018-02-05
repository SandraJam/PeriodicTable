package com.sandra.dupre.mendeleivpower.android.detail

import com.sandra.dupre.mendeleivpower.android.viewModel.AtomViewModel

interface DetailView {

    fun displayAtom(viewModel: AtomViewModel)

    fun displayError()
}