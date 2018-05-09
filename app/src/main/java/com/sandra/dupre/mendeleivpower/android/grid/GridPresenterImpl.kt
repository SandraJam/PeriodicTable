package com.sandra.dupre.mendeleivpower.android.grid

import android.content.res.Resources
import com.sandra.dupre.mendeleivpower.android.FamilyFormatter
import com.sandra.dupre.mendeleivpower.kernel.grid.GridPresenter

class GridPresenterImpl(
        private val view: GridView,
        private val resources: Resources,
        private val familyFormatter: FamilyFormatter) : GridPresenter {
}