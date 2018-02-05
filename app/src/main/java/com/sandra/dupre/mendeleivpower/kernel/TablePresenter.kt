package com.sandra.dupre.mendeleivpower.kernel

import com.sandra.dupre.mendeleivpower.kernel.entity.Atom

interface TablePresenter {
    fun presentTable(atoms: List<Atom>)
}