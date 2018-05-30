package com.sandra.dupre.mendeleivpower.android.grid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sandra.dupre.mendeleivpower.android.viewModel.GridViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.IndexViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel

class GridAdapter : RecyclerView.Adapter<GridViewHolder>() {

    var list = listOf<GridViewModel>()
    lateinit var listener: GridViewHolder.OnClickDetail

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemViewType(position: Int) =
            when(list[position]) {
                is ResumeAtomViewModel -> GridAtomViewHolder.layout
                is IndexViewModel -> GridIndexViewHolder.layout
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            when(viewType) {
                GridAtomViewHolder.layout -> GridAtomViewHolder(LayoutInflater.from(parent.context).inflate(GridAtomViewHolder.layout, parent, false), listener)
                GridIndexViewHolder.layout -> GridIndexViewHolder(LayoutInflater.from(parent.context).inflate(GridIndexViewHolder.layout, parent, false))
                else -> throw Exception()
            }
}