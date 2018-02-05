package com.sandra.dupre.mendeleivpower.android.detail

import android.content.res.Resources
import com.sandra.dupre.mendeleivpower.R
import com.sandra.dupre.mendeleivpower.android.FamilyFormatter
import com.sandra.dupre.mendeleivpower.android.AtomPresenterHelper
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

    lateinit var presenter: DetailPresenterImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = DetailPresenterImpl(view, AtomPresenterHelper(FamilyFormatter(), resources))
    }

    @Test
    fun testPresentDetailWhenAtomExistShouldDisplayAtom() {
        given(resources.getString(R.string.atom_number, 8))
                .willReturn("Number 8")
        presenter.presentDetail(Atom("D", "Dd", 8, FamilyAtom.ACTINIDE,
                1, 1, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE))

        then(view).should(only()).displayAtom(
                ResumeAtomViewModel("D", "Dd", "Number 8", R.color.actinide_color)
        )
    }

    @Test
    fun testPresentDetailWhenAtomNotExistShouldDisplayError() {
        presenter.presentDetail(null)
        then(view).should(only()).displayError()
    }

}