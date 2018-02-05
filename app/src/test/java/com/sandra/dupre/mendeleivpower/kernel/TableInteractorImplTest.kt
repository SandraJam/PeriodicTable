package com.sandra.dupre.mendeleivpower.kernel

import com.sandra.dupre.mendeleivpower.kernel.entity.Atom
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom
import org.junit.Before

import org.junit.Test
import org.mockito.*
import org.mockito.BDDMockito.*
import org.mockito.Mockito.*
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
            createAtom("Ga", "Gg", 11, FamilyAtom.ACTINIDE)
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

    @Test
    fun testSearchAtomsWhenIsEmptyShouldDoNothing() {
        interactor.searchAtoms("")

        then(tablePresenter).should(only()).presentTable(list)
    }

    @Test
    fun testSearchAtomsWhenIsFullOfWhiteSpaceShouldDoNothing() {
        interactor.searchAtoms("     ")

        then(tablePresenter).should(only()).presentTable(list)
    }

    @Test
    fun testSearchAtomsWhenNamesContainsQueryShouldCallPresenter() {
        interactor.searchAtoms("e")

        then(tablePresenter).should(Mockito.only()).presentTable(listOf(
                createAtom("E", "Ee", 9, FamilyAtom.NOBLE_GAS)))
    }

    @Test
    fun testSearchAtomsWhenNumberContainsQueryShouldCallPresenter() {
        interactor.searchAtoms("1")

        then(tablePresenter).should(Mockito.only()).presentTable(listOf(
                createAtom("F", "Ff", 10, FamilyAtom.DIATOMIC_NON_METAL),
                createAtom("Ga", "Gg", 11, FamilyAtom.ACTINIDE)))
    }

    @Test
    fun testSearchAtomsWhenSymbolContainsQueryShouldCallPresenter() {
        interactor.searchAtoms("a")

        then(tablePresenter).should(Mockito.only()).presentTable(listOf(
                createAtom("A", "Aa", 5, FamilyAtom.ACTINIDE),
                createAtom("Ga", "Gg", 11, FamilyAtom.ACTINIDE)))
    }

    private fun createAtom(symbol: String, name: String, number: Int, family: FamilyAtom) =
            Atom(symbol, name, number, family, 1, 1, 0f, 0f, 0f, 0f)

}