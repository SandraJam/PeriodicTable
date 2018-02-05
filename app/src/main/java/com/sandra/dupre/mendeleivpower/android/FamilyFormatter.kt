package com.sandra.dupre.mendeleivpower.android

import com.sandra.dupre.mendeleivpower.R
import com.sandra.dupre.mendeleivpower.kernel.entity.Atom
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom
import com.sandra.dupre.mendeleivpower.kernel.entity.FamilyAtom.*

open class FamilyFormatter {
    open fun getColor(atom: Atom) =
            when(atom.family) {
                ALKALI_METAL -> R.color.alkali_metal_color
                ALKALINE_EARTH_METAL -> R.color.alkaline_earth_metal_color
                LANTHANIDE -> R.color.lanthanide_color
                ACTINIDE -> R.color.actinide_color
                TRANSITION_METAL -> R.color.transition_metal_color
                POST_TRANSITION_METAL -> R.color.post_transition_metal_color
                METALLOID -> R.color.metalloid_color
                POLYATOMIC_NON_METAL -> R.color.polyatomic_non_metal_color
                DIATOMIC_NON_METAL -> R.color.diatomic_non_metal_color
                NOBLE_GAS -> R.color.noble_gas_color
                else -> R.color.undefined_color
            }

    open fun getLabel(atom: Atom) =
            when(atom.family) {
                ALKALI_METAL -> R.string.alkali_metal
                ALKALINE_EARTH_METAL -> R.string.alkaline_earth_metal
                LANTHANIDE -> R.string.lanthanide
                ACTINIDE -> R.string.actinide
                TRANSITION_METAL -> R.string.transition_metal
                POST_TRANSITION_METAL -> R.string.post_transition_metal
                METALLOID -> R.string.metalloid
                POLYATOMIC_NON_METAL -> R.string.polyatomic_nonmetal
                DIATOMIC_NON_METAL -> R.string.diatomic_nonmetal
                NOBLE_GAS -> R.string.noble_gas
                else -> R.string.undefined
            }
}