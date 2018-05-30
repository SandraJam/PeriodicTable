package com.sandra.dupre.mendeleivpower.kernel.grid

import com.sandra.dupre.mendeleivpower.kernel.AtomsRepository
import com.sandra.dupre.mendeleivpower.kernel.entity.Atom
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom
import org.junit.Before

import org.junit.Test
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GridInteractorImplTest {
    @Mock
    private lateinit var repository: AtomsRepository
    @Mock
    private lateinit var gridPresenter: GridPresenter

    @InjectMocks
    private lateinit var interactor: GridInteractorImpl

    private val a = createAtom("A", "Aa", 5, FamilyAtom.ACTINIDE, 5, 2)
    private val b = createAtom("B", "Bb", 6, FamilyAtom.ALKALI_METAL, 8, 1)
    private val c = createAtom("C", "Cc", 7, FamilyAtom.NOBLE_GAS, 2, 17)
    private val d = createAtom("D", "Dd", 8, FamilyAtom.ACTINIDE, 3, 14)
    private val x = createAtom("X", "Xx", 20, FamilyAtom.LANTHANIDE, 3, 14)
    private val e = createAtom("E", "Ee", 9, FamilyAtom.NOBLE_GAS, 7, 11)
    private val f = createAtom("F", "Ff", 10, FamilyAtom.DIATOMIC_NON_METAL, 9, 5)
    private val z = createAtom("Z", "Zz", 21, FamilyAtom.ACTINIDE, 4, 5)
    private val g = createAtom("Ga", "Gg", 11, FamilyAtom.ACTINIDE, 9, 7)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun testLoadAtoms() {
        given(repository.getAtoms()).willReturn(listOf(a, b, c, d, x, e, f, z, g))

        interactor.loadAtoms()

        //then(gridPresenter).should(Mockito.only()).presentGridAtoms()
    }

    private fun createAtom(symbol: String, name: String, number: Int, family: FamilyAtom, period: Int, group: Int) =
            Atom(symbol, name, number, family, group, period, 0f, 0f, 0f, 0f)

    private fun createEmptyAtom(group: Int, period: Int) =
            Atom("", "", 0, FamilyAtom.UNDEFINED, group, period, 0f, 0f, 0f, 0f)

}