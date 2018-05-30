package com.sandra.dupre.mendeleivpower.android.grid

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.sandra.dupre.mendeleivpower.R
import com.sandra.dupre.mendeleivpower.android.viewModel.GridViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.IndexViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel
import kotlinx.android.synthetic.main.cell_atom_grid.view.*
import kotlinx.android.synthetic.main.cell_index_grid.view.*

sealed class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    interface OnClickDetail {
        fun displayDetail(symbol: String)
    }

    abstract fun bind(gridViewModel: GridViewModel)
}

class GridAtomViewHolder(itemView: View, private val listener: OnClickDetail) : GridViewHolder(itemView) {

    companion object {
        const val layout: Int = R.layout.cell_atom_grid
    }

    override fun bind(gridViewModel: GridViewModel) {
        (gridViewModel as ResumeAtomViewModel).let { viewModel ->
            if (viewModel.isVisible) {
                itemView.visibility = View.VISIBLE
                itemView.atomImageView.setColorFilter(ContextCompat.getColor(itemView.context, viewModel.color))
                itemView.atomSymbolTextView.text = viewModel.symbol

                itemView.setOnClickListener {
                    listener.displayDetail(viewModel.symbol)
                }
            } else {
                itemView.visibility = View.INVISIBLE
            }
        }
    }
}

class GridIndexViewHolder(itemView: View) : GridViewHolder(itemView) {

    companion object {
        const val layout: Int = R.layout.cell_index_grid
    }

    override fun bind(gridViewModel: GridViewModel) {
        itemView.indexTextView.text = (gridViewModel as IndexViewModel).index
    }
}