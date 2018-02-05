package com.sandra.dupre.mendeleivpower.kernel

import com.sandra.dupre.mendeleivpower.kernel.entity.Atom
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom
import org.junit.Before

import org.junit.Test
import org.mockito.*
import org.mockito.BDDMockito.*
import java.math.BigDecimal

class TableInteractorImplTest {
    @Mock lateinit var repository: AtomsRepository
    @Mock lateinit var tablePresenter: TablePresenter

    @InjectMocks lateinit var interactor: TableInteractorImpl

    private val list = listOf(
            createAtom("A", "Aa", 5, FamilyAtom.ACTINIDE),
            createAtom("B", "Bb", 6, FamilyAtom.ALKALI_METAL),
            createAtom("C", "Cc", 7, FamilyAtom.NOBLE_GAS),
            createAtom("D", "Dd", 8, FamilyAtom.ACTINIDE),
            createAtom("E", "Ee", 9, FamilyAtom.NOBLE_GAS),
            createAtom("F", "Ff", 10, FamilyAtom.DIATOMIC_NON_METAL),
            createAtom("G", "Gg", 11, FamilyAtom.ACTINIDE)
    )

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        given(repository.getAtoms()).willReturn(list)
    }

    @Test
    fun testGetAtoms() {
        interactor.findAtoms()

        then(tablePresenter).should(Mockito.only()).presentTable(list)
    }

    private fun createAtom(symbol: String, name: String, number: Int, family: FamilyAtom) =
            Atom(symbol, name, number, family, 1, 1, BigDecimal.ONE, BigDecimal.ONE,
                    BigDecimal.ONE, BigDecimal.ONE)

}