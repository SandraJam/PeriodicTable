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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.math.BigDecimal

class TablePresenterImplTest {
    @Mock lateinit var view: TableView
    @Mock lateinit var resources: Resources

    lateinit var presenter: TablePresenterImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = TablePresenterImpl(view, AtomPresenterHelper(FamilyFormatter(), resources))
    }

    @Test
    fun testPresentTable() {
        given(resources.getString(R.string.atom_number, 5))
                .willReturn("Number 5")
        val element = Atom("symbol", "name", 5, FamilyAtom.ACTINIDE, 1,
                1, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE)
        presenter.presentTable(listOf(element))

        then(view).should(Mockito.only()).displayTable(
                listOf(ResumeAtomViewModel("symbol", "name", "Number 5",
                        R.color.actinide_color))
        )
    }

}