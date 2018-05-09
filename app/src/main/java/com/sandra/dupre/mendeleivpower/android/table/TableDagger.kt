package com.sandra.dupre.mendeleivpower.android.table

import android.content.res.Resources
import com.sandra.dupre.mendeleivpower.android.AtomPresenterHelper
import com.sandra.dupre.mendeleivpower.android.FamilyFormatter
import com.sandra.dupre.mendeleivpower.android.main.MainComponent
import com.sandra.dupre.mendeleivpower.kernel.*
import com.sandra.dupre.mendeleivpower.kernel.table.TableInteractor
import com.sandra.dupre.mendeleivpower.kernel.table.TableInteractorDecorate
import com.sandra.dupre.mendeleivpower.kernel.table.TableInteractorImpl
import com.sandra.dupre.mendeleivpower.kernel.table.TablePresenter
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
    fun providesTableInteractor(
            repository: AtomsRepository,
            tablePresenter: TablePresenter
    ): TableInteractor =
            TableInteractorDecorate(
                    TableInteractorImpl(
                            repository,
                            tablePresenter
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

