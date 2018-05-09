package com.sandra.dupre.mendeleivpower.kernel

import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom
import com.sandra.dupre.mendeleivpower.kernel.interactor.TableInteractor

class TableInteractorImpl(
        private val repository: AtomsRepository,
        private val presenter: TablePresenter
) : TableInteractor {

    var familyAtom: FamilyAtom? = null
    var search: String = ""
    var translateAtoms: Map<String, String> = emptyMap()

    override fun findAtoms() {
        presenter.presentTable(getAtoms(), familyAtom)
    }

    override fun initTranslateList(translateAtoms: Map<String, String>) {
        this.translateAtoms = translateAtoms
    }

    override fun searchAtoms(query: String) {
        search = query
        query.trim().toLowerCase().let { queryTrim ->
            presenter.presentTable(
                    getAtoms().filter {
                        translateAtoms[it.symbol]?.toLowerCase()?.contains(queryTrim) ?: false ||
                                it.symbol.toLowerCase().contains(queryTrim) ||
                                it.number.toString().contains(queryTrim)
                    },
                    familyAtom
            )
        }

    }

    override fun createFamilyFilter() {
        presenter.presentFamilies(FamilyAtom.values().toList())
    }

    override fun filterByFamily(familyAtom: FamilyAtom) {
        this.familyAtom = familyAtom
        presenter.presentTable(getAtoms(), familyAtom)
    }

    override fun removeFilter() {
        this.familyAtom = null
        searchAtoms(search)
    }

    private fun getAtoms() =
            repository.getAtoms().let {
                if (familyAtom == null) {
                    it
                } else {
                    it.filter { it.family == familyAtom }
                }
            }

}