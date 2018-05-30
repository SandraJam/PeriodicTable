package com.sandra.dupre.mendeleivpower.android.viewModel

import android.support.annotation.ColorRes
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom

data class TableViewModel(
        val resumeAtomViewModels: List<ResumeAtomViewModel>,
        val isFilterActive: Boolean,
        val filterLabel: String
)

sealed class GridViewModel

data class ResumeAtomViewModel(
        val symbol: String,
        val name: String,
        val number: String,
        @ColorRes val color: Int,
        val isVisible: Boolean
): GridViewModel()

data class IndexViewModel(
        val index: String
): GridViewModel()

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

data class FamilyViewModel(
        val code: FamilyAtom,
        val label: String
)