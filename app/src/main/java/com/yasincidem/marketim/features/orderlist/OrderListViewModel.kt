package com.yasincidem.marketim.features.orderlist

import android.content.SharedPreferences
import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.yasincidem.marketim.core.MvRxViewModel
import com.yasincidem.marketim.network.OrderListService
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class OrderListViewModel(
    initialState: OrderListState,
    private val orderListService: OrderListService,
    private val sharedPreferencesEditor: SharedPreferences.Editor
) : MvRxViewModel<OrderListState>(initialState) {

    init {
        fetchOrderList()
    }

    private fun fetchOrderList() {
        orderListService.orderList().subscribeOn(Schedulers.io()).execute {
            copy(orders = it)
        }
    }

    fun requestExpand() {
        setState { copy(isExpanded = !isExpanded) }
    }

    fun setIfUserWillRemembered(value: Boolean) {
        sharedPreferencesEditor.putBoolean("remember_me", value)
        sharedPreferencesEditor.apply()
    }

    companion object : MvRxViewModelFactory<OrderListViewModel, OrderListState> {
        override fun create(viewModelContext: ViewModelContext, state: OrderListState): OrderListViewModel {
            val service: OrderListService by viewModelContext.activity.inject()
            val sharedPreferencesEditor: SharedPreferences.Editor by viewModelContext.activity.inject()
            return OrderListViewModel(state, service, sharedPreferencesEditor)
        }
    }
}
