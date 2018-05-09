package com.sandra.dupre.mendeleivpower.kernel.grid

import com.sandra.dupre.mendeleivpower.kernel.AtomsRepository
import com.sandra.dupre.mendeleivpower.kernel.entity.Atom
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom
import org.junit.Before

import org.junit.Test
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GridInteractorImplTest {
    @Mock private lateinit var repository: AtomsRepository
    @Mock private lateinit var gridPresenter: GridPresenter

    @InjectMocks private lateinit var interactor: GridInteractorImpl

    private val list = listOf(
            createAtom("A", "Aa", 5, FamilyAtom.ACTINIDE),
            createAtom("B", "Bb", 6, FamilyAtom.ALKALI_METAL),
            createAtom("C", "Cc", 7, FamilyAtom.NOBLE_GAS),
            createAtom("D", "Dd", 8, FamilyAtom.ACTINIDE),
            createAtom("E", "Ee", 9, FamilyAtom.NOBLE_GAS),
            createAtom("F", "Ff", 10, FamilyAtom.DIATOMIC_NON_METAL),
            createAtom("Ga", "Gg", 11, FamilyAtom.ACTINIDE)
    )

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testLoadAtoms() {
        given(repository.getAtoms()).willReturn(list)

        interactor.loadAtoms()

        then(gridPresenter).should(only()).presentGridAtoms(list)
    }

    private fun createAtom(symbol: String, name: String, number: Int, family: FamilyAtom) =
            Atom(symbol, name, number, family, 1, 1, 0f, 0f, 0f, 0f)

}