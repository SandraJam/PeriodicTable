package com.sandra.dupre.mendeleivpower.kernel.entity

data class Atom(
        val symbol: String,
        val name: String,
        val number: Int,
        val family: FamilyAtom,
        val group: Int,
        val period: Int,
        val mass: Float,
        val density: Float,
        val boil: Float,
        val melt: Float
)

enum class FamilyAtom {
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
}
