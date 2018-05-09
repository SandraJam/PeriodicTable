package com.sandra.dupre.mendeleivpower.android.table

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu

import com.sandra.dupre.mendeleivpower.R
import com.sandra.dupre.mendeleivpower.android.detail.DetailActivity
import com.sandra.dupre.mendeleivpower.android.main.MainDependencies
import com.sandra.dupre.mendeleivpower.kernel.interactor.TableInteractor
import kotlinx.android.synthetic.main.activity_table.*
import javax.inject.Inject
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.ArrayAdapter
import com.sandra.dupre.mendeleivpower.android.viewModel.FamilyViewModel
import com.sandra.dupre.mendeleivpower.android.viewModel.TableViewModel

class TableActivity : AppCompatActivity(), TableView {

    companion object {
        private const val REQUEST_CODE_FAIL = 1337
    }

    @Inject lateinit var interactor: TableInteractor
    @Inject lateinit var decorate: TableViewDecorator

    private val tableAdapter = TableAdapter()

    private var isFirstList = true

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

        atomsRecyclerView.layoutManager = LinearLayoutManager(this)
        atomsRecyclerView.adapter = tableAdapter

        familyFilterTextView.setOnClickListener {
            interactor.removeFilter()
        }

        decorate.view = this
        interactor.findAtoms()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_FAIL) {
            Snackbar.make(atomsRecyclerView, "Une erreur est survenue", Snackbar.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val searchItem = menu.findItem(R.id.search)

        if (searchItem != null) {
            (searchItem.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    // do nothing
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    interactor.searchAtoms(newText)
                    return false
                }
            })
        }

        menu.findItem(R.id.filter)?.setOnMenuItemClickListener {
            interactor.createFamilyFilter()
            false
        }

        return true
    }


    override fun onDestroy() {
        decorate.view = null
        super.onDestroy()
    }

    override fun displayTable(tableViewModel: TableViewModel) {
        if(isFirstList) {
            interactor.initTranslateList(
                    tableViewModel.resumeAtomViewModels.map { Pair(it.symbol, it.name) }.toMap()
            )
            isFirstList = false
        }

        tableAdapter.list = tableViewModel.resumeAtomViewModels
        tableAdapter.notifyDataSetChanged()

        familyFilterTextView.text = tableViewModel.filterLabel
        familyFilterTextView.visibility = if (tableViewModel.isFilterActive) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun displayFilter(families: List<FamilyViewModel>) {
        AlertDialog.Builder(this)
                .setTitle("Filter by family")
                .setAdapter(ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice)
                        .apply { addAll(families.map { it.label }) },
                        { dialog, position ->
                            interactor.filterByFamily(families[position].code)
                            dialog.dismiss()
                        })
                .create()
                .show()
    }
}

