package com.sandra.dupre.mendeleivpower.repository

import android.content.res.AssetManager
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.sandra.dupre.mendeleivpower.kernel.AtomsRepository
import com.sandra.dupre.mendeleivpower.kernel.entity.Atom
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom.*
import java.math.BigDecimal
import java.io.BufferedReader
import java.io.InputStreamReader


class AtomsRepositoryImpl(private val asset: AssetManager) : AtomsRepository {

    private val atomList by lazy {
        jacksonObjectMapper().readValue(
                BufferedReader(InputStreamReader(asset.open("atoms.json"))),
                Elements::class.java
        )
                .elements
                .map {
                    Atom(
                            it.symbol,
                            it.name,
                            it.number,
                            when (it.category) {
                                "alkali metal" -> ALKALI_METAL
                                "alkali earth metal" -> ALKALINE_EARTH_METAL
                                "lanthanide" -> LANTHANIDE
                                "actinide" -> ACTINIDE
                                "transition metal" -> TRANSITION_METAL
                                "post-transition metal" -> POST_TRANSITION_METAL
                                "metalloid" -> METALLOID
                                "polyatomic nonmetal" -> POLYATOMIC_NON_METAL
                                "diatomic nonmetal" -> DIATOMIC_NON_METAL
                                "noble gas" -> NOBLE_GAS
                                else -> UNDEFINED
                            },
                            it.group,
                            it.period,
                            it.atomic_mass.toFloat(),
                            it.density.toFloat(),
                            it.boil.toFloat(),
                            it.melt.toFloat()
                    )
                }
    }

    override fun getAtoms() = atomList
}

data class Elements(
        val elements: List<Element>
)

data class Element(
        val name: String,
        val atomic_mass: Double,
        val boil: Double,
        val category: String,
        val density: Double,
        val melt: Double,
        val number: Int,
        val period: Int,
        val symbol: String,
        val group: Int
)