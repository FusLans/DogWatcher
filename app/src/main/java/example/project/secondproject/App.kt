package example.project.secondproject

import android.app.Application
import example.project.data.DataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(DataModule.module)
        }
    }
}