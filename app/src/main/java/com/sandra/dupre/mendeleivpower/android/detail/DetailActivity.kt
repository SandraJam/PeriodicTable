package com.sandra.dupre.mendeleivpower.android.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import com.sandra.dupre.mendeleivpower.R
import com.sandra.dupre.mendeleivpower.android.main.MainDependencies
import com.sandra.dupre.mendeleivpower.android.viewModel.AtomViewModel
import com.sandra.dupre.mendeleivpower.kernel.detail.DetailInteractor
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.cell_detail_atom.view.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailView {
    companion object {
        private val INTENT_ATOM_SYMBOL = "atom_symbol"

        fun newIntent(context: Context, symbol: String): Intent {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(INTENT_ATOM_SYMBOL, symbol)
            return intent
        }
    }

    @Inject lateinit var interactor: DetailInteractor
    @Inject lateinit var decorate: DetailViewDecorator

    private var symbol: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        DaggerDetailComponent.builder().mainComponent(MainDependencies.instance.mainComponent)
                .build().inject(this)

        symbol = intent.getStringExtra(INTENT_ATOM_SYMBOL)
    }

    override fun onStart() {
        super.onStart()
        decorate.view = this
        interactor.findAtom(symbol)
    }

    override fun onStop() {
        decorate.view = null
        super.onStop()
    }

    override fun displayAtom(viewModel: AtomViewModel) {
        attributesView.removeAllViews()

        nameTextView.text = viewModel.name
        symbolTextView.text = viewModel.symbol
        val color = ContextCompat.getColor(this, viewModel.color)
        symbolTextView.setTextColor(color)
        rootView.setBackgroundColor(color)

        viewModel.attributs.forEach {
            attribute ->
            val view = LayoutInflater.from(attributesView.context).inflate(
                    R.layout.cell_detail_atom,
                    attributesView,
                    false
            )
            view.labelTextView.text = attribute.label
            view.valueTextView.text = attribute.value
            attributesView.addView(view)
        }
    }

    override fun displayError() {
        setResult(Activity.RESULT_OK)
        finish()
    }

}