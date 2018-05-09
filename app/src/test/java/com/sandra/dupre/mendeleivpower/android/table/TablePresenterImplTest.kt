package com.sandra.dupre.mendeleivpower.android.table

import android.content.res.Resources
import com.sandra.dupre.mendeleivpower.R
import com.sandra.dupre.mendeleivpower.android.FamilyFormatter
import com.sandra.dupre.mendeleivpower.android.AtomPresenterHelper
import com.sandra.dupre.mendeleivpower.android.viewModel.FamilyViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.TableViewModel
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
    @Mock lateinit var familyFormatter: FamilyFormatter
    @Mock lateinit var resources: Resources

    @InjectMocks lateinit var presenter: TablePresenterImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        given(familyFormatter.getLabel(FamilyAtom.NOBLE_GAS)).willReturn(34)
        given(familyFormatter.getLabel(FamilyAtom.UNDEFINED)).willReturn(43)
        given(resources.getString(34)).willReturn("Noble gas")
        given(resources.getString(43)).willReturn("unknown")
    }

    @Test
    fun testPresentTableWithFilter() {
        val atomViewModel = ResumeAtomViewModel("symbol", "name", "Number 5",
                R.color.actinide_color)
        val element = Atom("symbol", "name", 5, FamilyAtom.ACTINIDE, 1,
                1, 0f, 0f, 0f, 0f)
        given(helper.getResumeAtomViewModel(element)).willReturn(atomViewModel)
        presenter.presentTable(listOf(element), FamilyAtom.NOBLE_GAS)

        then(view).should(only()).displayTable(TableViewModel(
                listOf(atomViewModel), true, "Noble gas"
        ))
    }

    @Test
    fun testPresentTableWithoutFilter() {
        val atomViewModel = ResumeAtomViewModel("symbol", "name", "Number 5",
                R.color.actinide_color)
        val element = Atom("symbol", "name", 5, FamilyAtom.ACTINIDE, 1,
                1, 0f, 0f, 0f, 0f)
        given(helper.getResumeAtomViewModel(element)).willReturn(atomViewModel)

        presenter.presentTable(listOf(element), null)

        then(view).should(only()).displayTable(TableViewModel(
                listOf(atomViewModel), false, ""
        ))
    }

    @Test
    fun testPresentFamilies() {
        presenter.presentFamilies(listOf(FamilyAtom.NOBLE_GAS))

        then(view).should(only()).displayFilter(listOf(
                FamilyViewModel(FamilyAtom.NOBLE_GAS, "Noble gas")
        ))
    }

}