package com.sandra.dupre.mendeleivpower

import android.app.Application
import com.sandra.dupre.mendeleivpower.android.main.MainDependencies

class CustomApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        MainDependencies.init(this)
    }
}
