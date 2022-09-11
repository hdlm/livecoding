package com.me.livecoding

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.me.livecoding.di.Modules.appModule
import com.me.livecoding.ui.ShowMainScreen
import com.me.livecoding.ui.theme.LiveCodingTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this

        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }

        setContent {
            LiveCodingTheme {
                ShowMainScreen()
            }
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }
}
