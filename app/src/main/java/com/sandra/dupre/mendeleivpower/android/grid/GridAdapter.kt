package com.sandra.dupre.mendeleivpower.android.grid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sandra.dupre.mendeleivpower.R
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel

class GridAdapter : RecyclerView.Adapter<GridViewHolder>() {
    var list = listOf<ResumeAtomViewModel>()
    lateinit var listener: GridViewHolder.OnClickDetail

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            GridViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.cell_atom_grid, parent, false), listener)
}