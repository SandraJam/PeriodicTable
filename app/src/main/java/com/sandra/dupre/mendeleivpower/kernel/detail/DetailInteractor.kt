package com.sandra.dupre.mendeleivpower.kernel

interface DetailInteractor {
    fun findAtom(symbol: String)
}

class DetailInteractorDecorate(private val interactor: DetailInteractor): DetailInteractor {
    override fun findAtom(symbol: String) =
            Thread(Runnable {
                interactor.findAtom(symbol)
            }).start()
}

class DetailInteractorImpl(
        private val repository: AtomsRepository,
        private val detailPresenter: DetailPresenter
): DetailInteractor {
    override fun findAtom(symbol: String) {
        detailPresenter.presentDetail(repository.getAtoms().find { it.symbol == symbol })
    }
}