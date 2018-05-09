package com.sandra.dupre.mendeleivpower.kernel.grid

import com.sandra.dupre.mendeleivpower.kernel.AtomsRepository

interface GridInteractor

class GridInteractorDecorate(private val gridInteractorImpl: GridInteractorImpl) : GridInteractor

class GridInteractorImpl(
        private val repository: AtomsRepository,
        private val gridPresenter: GridPresenter) : GridInteractor