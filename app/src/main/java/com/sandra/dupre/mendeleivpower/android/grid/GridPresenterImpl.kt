package com.sandra.dupre.mendeleivpower.android.grid

import com.sandra.dupre.mendeleivpower.android.AtomPresenterHelper
import com.sandra.dupre.mendeleivpower.android.viewModel.GridViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.IndexViewModel
import com.sandra.dupre.mendeleivpower.kernel.entity.Atom
import com.sandra.dupre.mendeleivpower.kernel.grid.GridPresenter

class GridPresenterImpl(
        private val view: GridView,
        private val helper: AtomPresenterHelper) : GridPresenter {

    override fun presentGridAtoms(mapGroupByAtoms: Map<Int, List<Atom>>) {
        val mutableGroupByAtoms: MutableMap<Int, List<GridViewModel>> = mapGroupByAtoms
                .mapValues {
                    listOf(IndexViewModel(it.value.first().group.toString())) +
                            it.value.map { helper.getResumeAtomViewModel(it) }
                }
                .toMutableMap()

        mutableGroupByAtoms[0] = (0..10).toList().map {
            IndexViewModel(when (it) {
                0, 8, 9, 10 -> ""
                else -> it.toString()
            })
        }

        view.displayGridAtoms(mutableGroupByAtoms.toSortedMap().flatMap { it.value })
    }
}