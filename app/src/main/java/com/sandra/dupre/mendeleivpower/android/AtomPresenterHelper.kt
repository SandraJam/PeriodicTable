package com.sandra.dupre.mendeleivpower.android

import android.content.res.Resources
import com.sandra.dupre.mendeleivpower.R
import com.sandra.dupre.mendeleivpower.android.viewModel.AtomViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.AttributViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel
import com.sandra.dupre.mendeleivpower.kernel.entity.Atom

open class AtomPresenterHelper(
        private val familyFormatter: FamilyFormatter,
        private val resources: Resources
) {

    open fun getResumeAtomViewModel(atom: Atom) =
            ResumeAtomViewModel(
                    atom.symbol,
                    atom.name,
                    resources.getString(R.string.atom_number, atom.number),
                    familyFormatter.getColor(atom),
                    atom.name.isNotEmpty()
            )

    open fun getAtomViewModel(atom: Atom) =
            AtomViewModel(
                    atom.symbol,
                    atom.name,
                    familyFormatter.getColor(atom),
                    listOf(
                            AttributViewModel(
                                    resources.getString(R.string.number),
                                    atom.number.toString()
                            ),
                            AttributViewModel(
                                    resources.getString(R.string.family),
                                    resources.getString(familyFormatter.getLabelByAtom(atom))
                            ),
                            AttributViewModel(
                                    resources.getString(R.string.group),
                                    atom.group.toString()
                            ),
                            AttributViewModel(
                                    resources.getString(R.string.period),
                                    atom.period.toString()
                            ),
                            AttributViewModel(
                                    resources.getString(R.string.mass),
                                    resources.getString(R.string.atom_mass, atom.mass.toString())
                            ),
                            AttributViewModel(
                                    resources.getString(R.string.density),
                                    resources.getString(R.string.atom_density, atom.density.toString())
                            ),
                            AttributViewModel(
                                    resources.getString(R.string.boil),
                                    resources.getString(R.string.atom_kelvin, atom.boil.toString())
                            ),
                            AttributViewModel(
                                    resources.getString(R.string.melt),
                                    resources.getString(R.string.atom_kelvin, atom.melt.toString())
                            )
                    )
            )

}