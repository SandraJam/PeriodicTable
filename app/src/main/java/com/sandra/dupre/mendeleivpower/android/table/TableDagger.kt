package com.sandra.dupre.mendeleivpower.android.table

import com.sandra.dupre.mendeleivpower.android.AtomPresenterHelper
import com.sandra.dupre.mendeleivpower.android.main.MainComponent
import com.sandra.dupre.mendeleivpower.android.main.MainModule
import com.sandra.dupre.mendeleivpower.kernel.*
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention
annotation class TableScope

@Module
class TableModule {

    @TableScope @Provides
    fun providesTableViewDecorate(): TableViewDecorator = TableViewDecorator()

    @Provides
    fun providesTableView(tableViewDecorator: TableViewDecorator): TableView = tableViewDecorator

    @Provides
    fun providesTablePresenter(
            view: TableView,
            helper: AtomPresenterHelper
    ): TablePresenter = TablePresenterImpl(view, helper)

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
        dependencies = arrayOf(MainComponent::class),
        modules = arrayOf(TableModule::class)
)
interface TableComponent {
    fun inject(activity: TableActivity)
}

