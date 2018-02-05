package com.sandra.dupre.mendeleivpower.android.main

import android.content.Context
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
    fun providesAtomsRepository(): AtomsRepository = AtomsRepositoryImpl(context.assets)

    @Singleton @Provides
    fun providesAtomPresenterHelper() = AtomPresenterHelper(
            FamilyFormatter(),
            context.resources
    )
}

@Singleton @Component(modules = arrayOf(MainModule::class)) interface MainComponent {
    fun repository(): AtomsRepository

    fun helper(): AtomPresenterHelper
}