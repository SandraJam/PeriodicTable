package com.sandra.dupre.mendeleivpower.kernel.interactor

import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom
import com.sandra.dupre.mendeleivpower.kernel.interactor.TableInteractor

class TableInteractorDecorate(private val interactor: TableInteractor) : TableInteractor {
    override fun findAtoms() =
            Thread(Runnable {
                interactor.findAtoms()
            }).start()

    override fun initTranslateList(translateAtoms: Map<String, String>) =
            Thread(Runnable {
                interactor.initTranslateList(translateAtoms)
            }).start()

    override fun searchAtoms(query: String) =
            Thread(Runnable {
                interactor.searchAtoms(query)
            }).start()

    override fun createFamilyFilter() =
            Thread(Runnable {
                interactor.createFamilyFilter()
            }).start()

    override fun filterByFamily(familyAtom: FamilyAtom) =
            Thread(Runnable {
                interactor.filterByFamily(familyAtom)
            }).start()

    override fun removeFilter() =
            Thread(Runnable {
                interactor.removeFilter()
            }).start()
}