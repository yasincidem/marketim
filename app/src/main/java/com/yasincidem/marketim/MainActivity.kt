package com.yasincidem.marketim

import android.os.Bundle
import android.util.Log
import com.airbnb.mvrx.BaseMvRxActivity
import io.reactivex.plugins.RxJavaPlugins

class MainActivity : BaseMvRxActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RxJavaPlugins.setErrorHandler { e ->
            Log.w("RXJavaError: ", e.message)
        }


    }
}
