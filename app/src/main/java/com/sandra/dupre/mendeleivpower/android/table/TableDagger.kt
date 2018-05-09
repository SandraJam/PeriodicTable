package com.sandra.dupre.mendeleivpower.android.table

import android.content.res.Resources
import com.sandra.dupre.mendeleivpower.android.AtomPresenterHelper
import com.sandra.dupre.mendeleivpower.android.FamilyFormatter
import com.sandra.dupre.mendeleivpower.android.main.MainComponent
import com.sandra.dupre.mendeleivpower.kernel.*
import com.sandra.dupre.mendeleivpower.kernel.interactor.TableInteractor
import com.sandra.dupre.mendeleivpower.kernel.interactor.TableInteractorDecorate
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention
annotation class TableScope

@Module
class TableModule {

    @TableScope
    @Provides
    fun providesTableViewDecorate(): TableViewDecorator = TableViewDecorator()

    @Provides
    fun providesTableView(tableViewDecorator: TableViewDecorator): TableView = tableViewDecorator

    @Provides
    fun providesTablePresenter(
            view: TableView,
            helper: AtomPresenterHelper,
            resources: Resources
    ): TablePresenter = TablePresenterImpl(view, helper, resources, FamilyFormatter())

    @Provides
    fun providesDetailInteractor(
            repository: AtomsRepository,
            detailPresenter: TablePresenter
    ): TableInteractor =
            TableInteractorDecorate(
                    TableInteractorImpl(
                            repository,
                            detailPresenter
                    )
            )
}

@TableScope
@Component(
        dependencies = [(MainComponent::class)],
        modules = [(TableModule::class)]
)
interface TableComponent {
    fun inject(activity: TableActivity)
}

