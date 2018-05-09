package com.sandra.dupre.mendeleivpower.android.grid

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.sandra.dupre.mendeleivpower.R
import com.sandra.dupre.mendeleivpower.android.detail.DetailActivity
import com.sandra.dupre.mendeleivpower.android.main.MainDependencies
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel
import com.sandra.dupre.mendeleivpower.kernel.grid.GridInteractor
import kotlinx.android.synthetic.main.activity_grid.*
import javax.inject.Inject

class GridActivity : AppCompatActivity(), GridView {

    companion object {
        fun newIntent(context: Context) = Intent(context, GridActivity::class.java)
    }

    @Inject lateinit var interactor: GridInteractor
    @Inject lateinit var decorate: GridViewDecorate

    private val adapter = GridAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid)

        DaggerGridComponent.builder()
                .mainComponent(MainDependencies.instance.mainComponent)
                .build()
                .inject(this)

        setSupportActionBar(toolbar)

        adapter.listener = object: GridViewHolder.OnClickDetail {
            override fun displayDetail(symbol: String) {
                startActivity(DetailActivity.newIntent(baseContext, symbol))
            }
        }

        gridRecyclerView.layoutManager = GridLayoutManager(this, 9, GridLayoutManager.HORIZONTAL, false)
        gridRecyclerView.adapter = adapter

        decorate.view = this
        interactor.loadAtoms()
    }

    override fun onDestroy() {
        decorate.view = null
        super.onDestroy()
    }

    override fun displayGridAtoms(listAtoms: List<ResumeAtomViewModel>) {
        adapter.list = listAtoms
        adapter.notifyDataSetChanged()
    }
}