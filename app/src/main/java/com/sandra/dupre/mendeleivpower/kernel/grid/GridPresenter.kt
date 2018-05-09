package com.sandra.dupre.mendeleivpower.kernel.grid

import com.sandra.dupre.mendeleivpower.kernel.entity.Atom

interface GridPresenter {
    fun presentGridAtoms(listAtoms: List<Atom>)
}