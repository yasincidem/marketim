package com.yasincidem.marketim.network

import com.yasincidem.marketim.models.Order
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface OrderListService {
    @Headers("Accept: application/json")
    @GET(".") // Base url ile istek yapılan url'in aynı olması durumunda "." koyulması gerekiyor. https://stackoverflow.com/a/40062661/6382158
    fun orderList(): Observable<List<Order>>
}