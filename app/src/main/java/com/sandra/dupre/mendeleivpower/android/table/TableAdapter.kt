package com.sandra.dupre.mendeleivpower.android.table

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sandra.dupre.mendeleivpower.R
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel

class TableAdapter : RecyclerView.Adapter<TableViewHolder>() {
    var list = listOf<ResumeAtomViewModel>()
    lateinit var listener: TableViewHolder.OnClikDetail

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            TableViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.cell_atom, parent, false), listener)
}