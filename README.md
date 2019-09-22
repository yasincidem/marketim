Bu projede Android otopilotu airbnb/MvRx kütüphanesi kullandım. Mvrx, Kotlin, RxJava ve Android Architecture Component'leri kullanarak react benzeri bir state machine mantığı oluştur. Bu state machine immutable veri yapılarını kullanıyor ve bu sayede state değişimlerini diff algoritmasıyla algılayıp sadece değişmesi gereken view'i re-render etmemizi sağlıyor. 

Airbnb, MvRx ile birlikte kullanmak için kendi kütüphanesi olan Epoxy'yi öneriyor. Recyclerview için oldukça hızlı ve kolay bir geliştirme sunuyor.

*     "com.airbnb.android:epoxy:$epoxyVersion" --------------------- Recyclerview için kullandım.
*     "io.reactivex.rxjava2:rxjava:2.2.8"  ------------------------- Arka plan'da işlemleri için kullandım.
*     "com.squareup.retrofit2:retrofit:$retrofitVersion" ----------- API'dan veri çekmek için kullandım.
*     "com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion" ---- API'dan gelen veriyi Observable olarak kullanabilmek için
*     "com.squareup.retrofit2:converter-moshi:$retrofitVersion"----- Retrofit'teki veriyi moshi'ye çevirmek için kullandım.
*     "com.squareup.moshi:moshi:$moshiVersion"---------------------- API'dan gelen veriyi kotlin objeme çevirmek için kullandım.
*     "androidx.navigation:navigation-ui-ktx:$navVersion" ---------- Sayfa geçişlerini geleneksel olarak Intent'ler ile yapmak yerine yeni bir architecture component olan androidx.navigation ile yapmak istedim
*     "org.koin:koin-android:$koinVersion" ------------------------- Dependency Injection için minimal bir kütüphane
*     "androidx.core:core-ktx:$ktxVersion" ------------------------- Kotlin, Java'daki spagetti kodları kısaltıyor ve ktx daha da ileri gidip kotlin dsl kullanarak kodları gereken yerlerde kısaltma imkanı veriyor.
*     "com.airbnb.android:mvrx:$mvrxVersion" ----------------------- React benzeri architecture kullanabilmek için.
*     'com.android.support:multidex:1.0.3' ------------------------- Android 5.0 altı sürümlerde otomatik bir multidex olmadığından dependency olarak eklemem gerekti.


![rsz_21rsz_11rsz_device-2019-09-22-001044](https://user-images.githubusercontent.com/13544246/65379462-00ba5d80-dcd1-11e9-9969-d3a8b2a0ad69.png)
