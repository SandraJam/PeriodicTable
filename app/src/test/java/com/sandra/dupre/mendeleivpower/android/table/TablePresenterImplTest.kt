package com.sandra.dupre.mendeleivpower.android.table

import android.content.res.Resources
import com.sandra.dupre.mendeleivpower.R
import com.sandra.dupre.mendeleivpower.android.FamilyFormatter
import com.sandra.dupre.mendeleivpower.android.AtomPresenterHelper
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel
import com.sandra.dupre.mendeleivpower.kernel.entity.Atom
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom
import org.junit.Before

import org.junit.Test
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class TablePresenterImplTest {
    @Mock lateinit var view: TableView
    @Mock lateinit var helper: AtomPresenterHelper

    @InjectMocks lateinit var presenter: TablePresenterImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testPresentTable() {
        val atomViewModel = ResumeAtomViewModel("symbol", "name", "Number 5",
                R.color.actinide_color)
        val element = Atom("symbol", "name", 5, FamilyAtom.ACTINIDE, 1,
                1, 0f, 0f, 0f, 0f)
        given(helper.getResumeAtomViewModel(element)).willReturn(atomViewModel)
        presenter.presentTable(listOf(element))

        then(view).should(Mockito.only()).displayTable(listOf(atomViewModel))
    }

}