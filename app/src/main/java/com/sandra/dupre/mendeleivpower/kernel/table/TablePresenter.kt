package com.sandra.dupre.mendeleivpower.kernel.table

import com.sandra.dupre.mendeleivpower.kernel.entity.Atom
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom

interface TablePresenter {
    fun presentTable(atoms: List<Atom>, filterLabel: FamilyAtom?)
    fun presentFamilies(families: List<FamilyAtom>)
}