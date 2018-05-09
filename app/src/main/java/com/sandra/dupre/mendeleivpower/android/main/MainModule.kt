package com.sandra.dupre.mendeleivpower.android.main

import android.content.Context
import android.content.res.Resources
import com.sandra.dupre.mendeleivpower.android.AtomPresenterHelper
import com.sandra.dupre.mendeleivpower.android.FamilyFormatter
import com.sandra.dupre.mendeleivpower.kernel.*
import com.sandra.dupre.mendeleivpower.repository.AtomsRepositoryImpl
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule(private val context: Context) {

    @Singleton @Provides
    fun providesResources() : Resources = context.resources

    @Singleton @Provides
    fun providesAtomsRepository(): AtomsRepository = AtomsRepositoryImpl(context.assets)

    @Singleton @Provides
    fun providesAtomPresenterHelper(
            resources: Resources
    ) = AtomPresenterHelper(FamilyFormatter(), resources)
}

@Singleton @Component(modules = [(MainModule::class)]) interface MainComponent {
    fun resources(): Resources

    fun repository(): AtomsRepository

    fun helper(): AtomPresenterHelper
}