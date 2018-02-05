package com.sandra.dupre.mendeleivpower.android.viewModel

import android.support.annotation.ColorRes

data class ResumeAtomViewModel(
        val symbol: String,
        val name: String,
        val number: String,
        @ColorRes val color: Int
)

data class AtomViewModel(
        val symbol: String,
        val name: String,
        @ColorRes val color: Int,
        val attributs: List<AttributViewModel>
)

data class AttributViewModel(
        val label: String,
        val value: String
)