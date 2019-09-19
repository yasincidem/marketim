package com.yasincidem.marketim.core

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.yasincidem.marketim.R
import com.yasincidem.marketim.network.OrderListService
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class MarketimApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        /**
         * Basit ve küçük bir Dependency Injection framework olan Koin'i kullanmak istedim. DSL'ler üzerinden yürüdüğü için
         * oldukça okunaklı. startKoin{ } verdiğimiz referanslara göre global bir container oluşturuyor.
         */
        startKoin {
            androidLogger()
            //Uygulama Context'ini belirtmemiz gerekiyor.
            androidContext(this@MarketimApplication)
            //Kullnılacak modülleri belirtmemiz gerekiyor.
            modules(orderListServiceModule)
        }
    }
}

private val orderListServiceModule = module {
    //Burada factory içine yazılan objeyi her get() metodu çağırıldığında tekrar yaratacak.
    factory {
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    //Burada factory içine yazılan objeyi her get() metodu çağırıldığında tekrar yaratacak.
    factory {
        Retrofit.Builder()
            .baseUrl(androidContext().resources.getString(R.string.BASE_URL_ORDER_LIST))
            .addConverterFactory(MoshiConverterFactory.create(get<Moshi>()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    // single, OrderListService'inden singleton bir obje yaratmamızı sağlıyor.
    single {
        //get() eager olan ve Koin container'ından instance çeken bir metot
        get<Retrofit>().create(OrderListService::class.java)
    }
}