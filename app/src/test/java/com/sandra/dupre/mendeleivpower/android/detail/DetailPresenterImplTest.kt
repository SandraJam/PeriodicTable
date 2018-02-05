package com.sandra.dupre.mendeleivpower.android.detail

import android.content.res.Resources
import com.sandra.dupre.mendeleivpower.R
import com.sandra.dupre.mendeleivpower.android.FamilyFormatter
import com.sandra.dupre.mendeleivpower.android.AtomPresenterHelper
import com.sandra.dupre.mendeleivpower.android.viewModel.AtomViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.AttributViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel
import com.sandra.dupre.mendeleivpower.kernel.entity.Atom
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.BDDMockito.*
import java.math.BigDecimal

class DetailPresenterImplTest {
    @Mock lateinit var view: DetailView
    @Mock lateinit var resources: Resources
    @Mock lateinit var helper: AtomPresenterHelper

    @InjectMocks lateinit var presenter: DetailPresenterImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testPresentDetailWhenAtomExistShouldDisplayAtom() {
        val atom = Atom("D", "Dd", 8, FamilyAtom.ACTINIDE, 1,
                1, 0f, 0f, 0f, 0f)
        val atomViewModel = AtomViewModel("", "", 0, emptyList())
        given(resources.getString(R.string.atom_number, 8))
                .willReturn("Number 8")
        given(helper.getAtomViewModel(atom)).willReturn(atomViewModel)

        presenter.presentDetail(atom)

        then(view).should(only()).displayAtom(atomViewModel)
    }

    @Test
    fun testPresentDetailWhenAtomNotExistShouldDisplayError() {
        presenter.presentDetail(null)
        then(view).should(only()).displayError()
    }

}