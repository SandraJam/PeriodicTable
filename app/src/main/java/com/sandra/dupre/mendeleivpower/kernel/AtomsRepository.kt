package com.sandra.dupre.mendeleivpower.kernel

import com.sandra.dupre.mendeleivpower.kernel.entity.Atom

interface AtomsRepository {
    fun getAtoms(): List<Atom>
}