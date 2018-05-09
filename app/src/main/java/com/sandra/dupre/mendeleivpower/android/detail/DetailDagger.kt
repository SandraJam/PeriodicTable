package com.sandra.dupre.mendeleivpower.android.detail

import com.sandra.dupre.mendeleivpower.android.AtomPresenterHelper
import com.sandra.dupre.mendeleivpower.android.main.MainComponent
import com.sandra.dupre.mendeleivpower.kernel.*
import com.sandra.dupre.mendeleivpower.kernel.detail.DetailInteractor
import com.sandra.dupre.mendeleivpower.kernel.detail.DetailInteractorDecorate
import com.sandra.dupre.mendeleivpower.kernel.detail.DetailInteractorImpl
import com.sandra.dupre.mendeleivpower.kernel.detail.DetailPresenter
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
@Retention
annotation class DetailScope

@Module
class DetailModule {

    @DetailScope @Provides
    fun providesDetailViewDecorate(): DetailViewDecorator = DetailViewDecorator()

    @Provides
    fun providesDetailView(detailViewDecorator: DetailViewDecorator): DetailView = detailViewDecorator

    @Provides
    fun providesDetailPresenter(
            view: DetailView,
            helper: AtomPresenterHelper
    ): DetailPresenter = DetailPresenterImpl(view, helper)

    @Provides
    fun providesDetailInteractor(
            repository: AtomsRepository,
            detailPresenter: DetailPresenter
    ): DetailInteractor =
            DetailInteractorDecorate(
                    DetailInteractorImpl(
                            repository,
                            detailPresenter
                    )
            )
}

@DetailScope
@Component(
        dependencies = arrayOf(MainComponent::class),
        modules = arrayOf(DetailModule::class)
)
interface DetailComponent {
    fun inject(activity: DetailActivity)
}

