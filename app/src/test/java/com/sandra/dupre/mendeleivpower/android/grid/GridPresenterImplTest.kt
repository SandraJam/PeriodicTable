package com.sandra.dupre.mendeleivpower.android.grid

import com.sandra.dupre.mendeleivpower.R
import com.sandra.dupre.mendeleivpower.android.AtomPresenterHelper
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel
import com.sandra.dupre.mendeleivpower.kernel.entity.Atom
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom
import org.junit.Before

import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.BDDMockito.then
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.only
import org.mockito.MockitoAnnotations

class GridPresenterImplTest {
    @Mock private lateinit var view: GridView
    @Mock private lateinit var helper: AtomPresenterHelper

    @InjectMocks private lateinit var presenter: GridPresenterImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testPresentGridAtoms() {
        val atomViewModel = ResumeAtomViewModel("symbol", "name", "Number 5",
                R.color.actinide_color)
        val element = Atom("symbol", "name", 5, FamilyAtom.ACTINIDE, 1,
                1, 0f, 0f, 0f, 0f)
        BDDMockito.given(helper.getResumeAtomViewModel(element)).willReturn(atomViewModel)
        presenter.presentGridAtoms(listOf(element))

        then(view).should(only()).displayGridAtoms(listOf(atomViewModel))
    }

}