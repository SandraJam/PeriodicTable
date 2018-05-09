package com.sandra.dupre.mendeleivpower.android.grid

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel
import kotlinx.android.synthetic.main.cell_atom_grid.view.*

class GridViewHolder(itemView: View, private val listener: OnClickDetail) : RecyclerView.ViewHolder(itemView) {

    fun bind(viewModel: ResumeAtomViewModel) {
        itemView.atomImageView.setColorFilter(ContextCompat.getColor(itemView.context, viewModel.color))
        itemView.atomSymbolTextView.text = viewModel.symbol

        itemView.setOnClickListener {
            listener.displayDetail(viewModel.symbol)
        }
    }

    interface OnClickDetail {
        fun displayDetail(symbol: String)
    }
}