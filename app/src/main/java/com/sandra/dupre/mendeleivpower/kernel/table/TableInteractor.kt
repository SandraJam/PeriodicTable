package com.sandra.dupre.mendeleivpower.kernel.interactor

import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom

interface TableInteractor {
    fun findAtoms()
    fun initTranslateList(translateAtoms: Map<String, String>)
    fun searchAtoms(query: String)
    fun createFamilyFilter()
    fun filterByFamily(familyAtom: FamilyAtom)
    fun removeFilter()
}