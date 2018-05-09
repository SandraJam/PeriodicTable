package com.sandra.dupre.mendeleivpower.android.table

import android.content.res.Resources
import com.sandra.dupre.mendeleivpower.android.AtomPresenterHelper
import com.sandra.dupre.mendeleivpower.android.FamilyFormatter
import com.sandra.dupre.mendeleivpower.android.viewModel.FamilyViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.TableViewModel
import com.sandra.dupre.mendeleivpower.kernel.table.TablePresenter
import com.sandra.dupre.mendeleivpower.kernel.entity.Atom
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom

class TablePresenterImpl(
        private val view: TableView,
        private val helper: AtomPresenterHelper,
        private val resources: Resources,
        private val familyFormatter: FamilyFormatter
) : TablePresenter {

    override fun presentTable(atoms: List<Atom>, filterLabel: FamilyAtom?) {
        view.displayTable(TableViewModel(
                atoms.map { helper.getResumeAtomViewModel(it) },
                filterLabel != null,
                filterLabel?.let { resources.getString(familyFormatter.getLabel(it)) } ?: ""
        ))
    }

    override fun presentFamilies(families: List<FamilyAtom>) {
        view.displayFilter(families.map {
            FamilyViewModel(it, resources.getString(familyFormatter.getLabel(it)))
        })
    }
}