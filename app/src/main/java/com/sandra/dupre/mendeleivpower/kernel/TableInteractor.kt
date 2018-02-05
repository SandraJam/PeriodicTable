package com.sandra.dupre.mendeleivpower.kernel

interface TableInteractor {
    fun findAtoms()
}

class TableInteractorDecorate(private val interactor: TableInteractor): TableInteractor {
    override fun findAtoms() =
            Thread(Runnable {
                interactor.findAtoms()
            }).start()
}

class TableInteractorImpl(
        private val repository: AtomsRepository,
        private val presenter: TablePresenter
) : TableInteractor {

    override fun findAtoms() {
        presenter.presentTable(repository.getAtoms())
    }
}