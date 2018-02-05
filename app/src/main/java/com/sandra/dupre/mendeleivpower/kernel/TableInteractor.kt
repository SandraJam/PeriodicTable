package com.sandra.dupre.mendeleivpower.kernel

interface TableInteractor {
    fun findAtoms()
    fun searchAtoms(query: String?)
}

class TableInteractorDecorate(private val interactor: TableInteractor) : TableInteractor {
    override fun findAtoms() =
            Thread(Runnable {
                interactor.findAtoms()
            }).start()

    override fun searchAtoms(query: String?) =
            Thread(Runnable {
                interactor.searchAtoms(query)
            }).start()
}

class TableInteractorImpl(
        private val repository: AtomsRepository,
        private val presenter: TablePresenter
) : TableInteractor {
    override fun findAtoms() {
        presenter.presentTable(repository.getAtoms())
    }

    override fun searchAtoms(query: String?) {
        presenter.presentTable(
                if (query != null && query.isNotEmpty() && query.isNotBlank()) {
                    repository.getAtoms().filter {
                        it.name.toLowerCase().contains(query) ||
                                it.symbol.toLowerCase().contains(query) ||
                                it.number.toString().contains(query)
                    }
                } else {
                    repository.getAtoms()
                }
        )
    }
}