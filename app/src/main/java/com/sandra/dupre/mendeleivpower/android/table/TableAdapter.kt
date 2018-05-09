package com.sandra.dupre.mendeleivpower.android.table

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sandra.dupre.mendeleivpower.R
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel

class TableAdapter : RecyclerView.Adapter<TableViewHolder>() {
    var list = emptyList<ResumeAtomViewModel>()
    lateinit var listener: TableViewHolder.OnClikDetail

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: TableViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            TableViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.cell_atom, parent, false), listener)

    fun replace(list: List<ResumeAtomViewModel>) {
        val diffResult = DiffUtil.calculateDiff(TableDiffCallBack(this.list, list))
        this.list = list
        diffResult.dispatchUpdatesTo(this)
    }

    inner class TableDiffCallBack(
            private val oldList: List<ResumeAtomViewModel>,
            private val newList: List<ResumeAtomViewModel>) : DiffUtil.Callback() {

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldList[oldItemPosition] == newList[newItemPosition]

        override fun getOldListSize() = oldList.size

        override fun getNewListSize() = newList.size

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                oldList[oldItemPosition].color == newList[newItemPosition].color &&
                oldList[oldItemPosition].name == newList[newItemPosition].name &&
                oldList[oldItemPosition].number == newList[newItemPosition].number &&
                oldList[oldItemPosition].symbol == newList[newItemPosition].symbol

    }
}