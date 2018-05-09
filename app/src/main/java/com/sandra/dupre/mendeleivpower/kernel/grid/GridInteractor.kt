package com.sandra.dupre.mendeleivpower.kernel.grid

import com.sandra.dupre.mendeleivpower.kernel.AtomsRepository

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
        gridPresenter.presentGridAtoms(repository.getAtoms())
    }
}