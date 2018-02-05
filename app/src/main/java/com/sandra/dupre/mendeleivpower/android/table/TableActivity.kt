package com.sandra.dupre.mendeleivpower.android.table

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewAnimationUtils

import com.sandra.dupre.mendeleivpower.R
import com.sandra.dupre.mendeleivpower.android.detail.DetailActivity
import com.sandra.dupre.mendeleivpower.android.main.MainDependencies
import com.sandra.dupre.mendeleivpower.android.viewModel.ResumeAtomViewModel
import com.sandra.dupre.mendeleivpower.kernel.TableInteractor
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_table.*
import javax.inject.Inject

class TableActivity : AppCompatActivity(), TableView {

    companion object {
        private const val REQUEST_CODE_FAIL = 1337
    }

    @Inject lateinit var interactor: TableInteractor
    @Inject lateinit var decorate: TableViewDecorator

    private val tableAdapter = TableAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)

        DaggerTableComponent.builder().mainComponent(MainDependencies.instance.mainComponent)
                .build().inject(this)

        setSupportActionBar(toolbar)

        tableAdapter.listener = object : TableViewHolder.OnClikDetail {
            override fun displayDetail(symbol: String) {
                startActivityForResult(DetailActivity.newIntent(baseContext, symbol), REQUEST_CODE_FAIL)
            }
        }
        decorate.view = this
        interactor.findAtoms()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_FAIL) {
            Snackbar.make(atomsRecyclerView, "Une erreur est survenue", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        decorate.view = null
        super.onDestroy()
    }

    override fun displayTable(resumeAtoms: List<ResumeAtomViewModel>) {
        tableAdapter.list = resumeAtoms
        tableAdapter.notifyDataSetChanged()

        atomsRecyclerView.layoutManager = LinearLayoutManager(this)
        atomsRecyclerView.adapter = tableAdapter
    }
}

