package com.sandra.dupre.mendeleivpower.android.table

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel
import kotlinx.android.synthetic.main.cell_atom.view.*

class TableViewHolder(itemView: View, private val listener: OnClikDetail) : RecyclerView.ViewHolder(itemView) {

    fun bind(viewModel: ResumeAtomViewModel) {
        itemView.atomTextView.text = viewModel.name
        itemView.atomImageView.setColorFilter(ContextCompat.getColor(itemView.context, viewModel.color))
        itemView.atomSymbolTextView.text = viewModel.symbol
        itemView.atomNumberTextView.text = viewModel.number

        itemView.setOnClickListener {
            listener.displayDetail(viewModel.symbol)
        }
    }

    interface OnClikDetail {
        fun displayDetail(symbol: String)
    }
}