package com.sandra.dupre.mendeleivpower.kernel

import com.sandra.dupre.mendeleivpower.kernel.entity.Atom
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before

import org.junit.Test
import org.mockito.*
import org.mockito.BDDMockito.*
import org.mockito.Mockito

class TableInteractorImplTest {
    @Mock private lateinit var repository: AtomsRepository
    @Mock private lateinit var tablePresenter: TablePresenter

    @InjectMocks private lateinit var interactor: TableInteractorImpl

    private val list = listOf(
            createAtom("A", "Aa", 5, ACTINIDE),
            createAtom("B", "Bb", 6, ALKALI_METAL),
            createAtom("C", "Cc", 7, NOBLE_GAS),
            createAtom("D", "Dd", 8, ACTINIDE),
            createAtom("E", "Ee", 9, NOBLE_GAS),
            createAtom("F", "Ff", 10, DIATOMIC_NON_METAL),
            createAtom("Ga", "Gg", 11, ACTINIDE)
    )

    private val translateMap = mapOf(
            "A" to "Abricot", "B" to "Banane", "C" to "Coco", "D" to "Datte",
            "E" to "Epinard", "F" to "Fraise", "Ga" to "Grenadine"
    )

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        given(repository.getAtoms()).willReturn(list)
        interactor.translateAtoms = translateMap
    }

    @Test
    fun testGetAtoms() {
        interactor.findAtoms()

        then(tablePresenter).should(Mockito.only()).presentTable(list, null)
    }

    @Test
    fun testSearchAtomsWhenIsEmptyShouldDoNothing() {
        interactor.searchAtoms("")

        assertThat(interactor.search).isEqualTo("")
        then(tablePresenter).should(only()).presentTable(list, null)
    }

    @Test
    fun testSearchAtomsWhenIsFullOfWhiteSpaceShouldDoNothing() {
        interactor.searchAtoms("     ")

        assertThat(interactor.search).isEqualTo("     ")
        then(tablePresenter).should(only()).presentTable(list, null)
    }

    @Test
    fun testSearchAtomsWhenSymbolContainsQueryShouldCallPresenter() {
        interactor.searchAtoms("ga")

        assertThat(interactor.search).isEqualTo("ga")
        then(tablePresenter).should(Mockito.only()).presentTable(listOf(
                createAtom("Ga", "Gg", 11, ACTINIDE)), null)
    }

    @Test
    fun testSearchAtomsWhenNumberContainsQueryShouldCallPresenter() {
        interactor.searchAtoms("1")

        assertThat(interactor.search).isEqualTo("1")
        then(tablePresenter).should(Mockito.only()).presentTable(listOf(
                createAtom("F", "Ff", 10, DIATOMIC_NON_METAL),
                createAtom("Ga", "Gg", 11, ACTINIDE)), null)
    }

    @Test
    fun testSearchAtomsWhenTranslateNameContainsQueryShouldCallPresenter() {
        interactor.searchAtoms("na")

        assertThat(interactor.search).isEqualTo("na")
        then(tablePresenter).should(Mockito.only()).presentTable(listOf(
                createAtom("B", "Bb", 6, ALKALI_METAL),
                createAtom("E", "Ee", 9, NOBLE_GAS),
                createAtom("Ga", "Gg", 11, ACTINIDE)), null)
    }

    @Test
    fun testCreateFamilyFilterShouldCallPresenter() {
        interactor.createFamilyFilter()

        then(tablePresenter).should(only()).presentFamilies(listOf(
                ALKALI_METAL,
                ALKALINE_EARTH_METAL,
                LANTHANIDE,
                ACTINIDE,
                TRANSITION_METAL,
                POST_TRANSITION_METAL,
                METALLOID,
                POLYATOMIC_NON_METAL,
                DIATOMIC_NON_METAL,
                NOBLE_GAS,
                UNDEFINED
        ))
    }

    @Test
    fun testFilterByFamilyShouldCallPresenter() {
        interactor.filterByFamily(NOBLE_GAS)

        assertThat(interactor.familyAtom).isEqualTo(NOBLE_GAS)
        then(tablePresenter).should(only()).presentTable(listOf(
                createAtom("C", "Cc", 7, NOBLE_GAS),
                createAtom("E", "Ee", 9, NOBLE_GAS)
        ), NOBLE_GAS)
    }

    @Test
    fun testRemoveFilterShouldFamilyAtomToUndefinedAndCallPresenter() {
        interactor.removeFilter()

        assertThat(interactor.familyAtom).isEqualTo(null)
        then(tablePresenter).should(only()).presentTable(list, null)
    }

    @Test
    fun testRemoveFilterShouldFamilyAtomToUndefinedAndCallPresenterWithSearch() {
        interactor.search = "coco"

        interactor.removeFilter()

        assertThat(interactor.familyAtom).isEqualTo(null)
        then(tablePresenter).should(only()).presentTable(listOf(
                createAtom("C", "Cc", 7, NOBLE_GAS)
        ), null)
    }

    private fun createAtom(symbol: String, name: String, number: Int, family: FamilyAtom) =
            Atom(symbol, name, number, family, 1, 1, 0f, 0f, 0f, 0f)

}