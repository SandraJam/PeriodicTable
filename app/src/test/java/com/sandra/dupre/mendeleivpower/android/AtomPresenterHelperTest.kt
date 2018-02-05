package com.sandra.dupre.mendeleivpower.android

import android.content.res.Resources
import com.sandra.dupre.mendeleivpower.R
import com.sandra.dupre.mendeleivpower.android.viewModel.AtomViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.AttributViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel
import com.sandra.dupre.mendeleivpower.kernel.entity.Atom
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before

import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class AtomPresenterHelperTest {

    @Mock private lateinit var familyFormatter: FamilyFormatter
    @Mock private lateinit var resources: Resources

    @InjectMocks private lateinit var helper: AtomPresenterHelper

    private val atom = Atom("C", "cc", 0, FamilyAtom.ACTINIDE, 0,
            0, 0f, 0f, 0f, 0f)


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        given(familyFormatter.getColor(atom)).willReturn(0)
    }

    @Test
    fun testGetResumeAtomViewModel() {
        given(resources.getString(R.string.atom_number, 0)).willReturn("atom 0")

        val viewModel = helper.getResumeAtomViewModel(atom)

        assertThat(viewModel).isEqualTo(
                ResumeAtomViewModel("C", "cc", "atom 0", 0)
        )
    }

    @Test
    fun testGetAtomViewModel() {
        given(resources.getString(R.string.number)).willReturn("number")
        given(resources.getString(R.string.family)).willReturn("family")
        given(resources.getString(R.string.group)).willReturn("group")
        given(resources.getString(R.string.period)).willReturn("period")
        given(resources.getString(R.string.mass)).willReturn("mass")
        given(resources.getString(R.string.density)).willReturn("density")
        given(resources.getString(R.string.boil)).willReturn("boil")
        given(resources.getString(R.string.melt)).willReturn("melt")
        given(familyFormatter.getLabel(atom)).willReturn(0)
        given(resources.getString(0)).willReturn("label")
        given(resources.getString(R.string.atom_mass, "0.0")).willReturn("0m")
        given(resources.getString(R.string.atom_density, "0.0")).willReturn("0d")
        given(resources.getString(R.string.atom_kelvin, "0.0")).willReturn("0k")

        val atomViewModel = helper.getAtomViewModel(atom)

        assertThat(atomViewModel).isEqualTo(
                AtomViewModel(
                        "C", "cc", 0,
                        listOf(
                                AttributViewModel("number", "0"),
                                AttributViewModel("family", "label"),
                                AttributViewModel("group", "0" ),
                                AttributViewModel("period", "0"),
                                AttributViewModel("mass","0m" ),
                                AttributViewModel("density", "0d" ),
                                AttributViewModel("boil", "0k"),
                                AttributViewModel("melt", "0k")
                        )
                )
        )
    }

}