package com.sandra.dupre.mendeleivpower.kernel.grid

import com.sandra.dupre.mendeleivpower.kernel.AtomsRepository
import com.sandra.dupre.mendeleivpower.kernel.entity.Atom
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom

interface GridInteractor {
    fun loadAtoms()
}

class GridInteractorDecorate(private val interactor: GridInteractorImpl) : GridInteractor {
    override fun loadAtoms() =
            Thread(Runnable {
                interactor.loadAtoms()
            }).start()
}

class GridInteractorImpl(
        private val repository: AtomsRepository,
        private val gridPresenter: GridPresenter) : GridInteractor {
    override fun loadAtoms() {
        val listAtoms =repository.getAtoms()
                .map {
                    when(it.family) {
                        FamilyAtom.LANTHANIDE -> it.copy(period = 9)
                        FamilyAtom.ACTINIDE -> it.copy(period = 10)
                        else -> it
                    }
                }
                .let { atoms ->
                    (1..18).map { group ->
                        (1..10).map { period ->
                            atoms.find { it.group == group && it.period == period } ?: createEmptyAtom(group, period)
                        }
                    }
                }
                .flatten()


        gridPresenter.presentGridAtoms(listAtoms)
    }

    private fun createEmptyAtom(group: Int, period: Int) =
            Atom("", "", 0, FamilyAtom.UNDEFINED, group, period, 0f, 0f, 0f, 0f)

}