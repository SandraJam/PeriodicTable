package com.sandra.dupre.mendeleivpower.android.grid

import android.content.res.Resources
import com.sandra.dupre.mendeleivpower.android.FamilyFormatter
import com.sandra.dupre.mendeleivpower.android.main.MainComponent
import com.sandra.dupre.mendeleivpower.kernel.AtomsRepository
import com.sandra.dupre.mendeleivpower.kernel.grid.GridInteractor
import com.sandra.dupre.mendeleivpower.kernel.grid.GridInteractorDecorate
import com.sandra.dupre.mendeleivpower.kernel.grid.GridInteractorImpl
import com.sandra.dupre.mendeleivpower.kernel.grid.GridPresenter
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention
annotation class GridScope

@Module
class GridModule {

    @GridScope
    @Provides
    fun providesGridViewDecorate(): GridViewDecorate = GridViewDecorate()

    @Provides
    fun providesGridView(gridViewDecorator: GridViewDecorate): GridView = gridViewDecorator

    @Provides
    fun providesGridPresenter(
            view: GridView,
            resources: Resources
    ): GridPresenter = GridPresenterImpl(view, resources, FamilyFormatter())

    @Provides
    fun providesDetailInteractor(
            repository: AtomsRepository,
            gridPresenter: GridPresenter
    ): GridInteractor =
            GridInteractorDecorate(
                    GridInteractorImpl(
                            repository,
                            gridPresenter
                    )
            )
}

@GridScope
@Component(
        dependencies = [(MainComponent::class)],
        modules = [(GridModule::class)]
)
interface GridComponent {
    fun inject(activity: GridActivity)
}