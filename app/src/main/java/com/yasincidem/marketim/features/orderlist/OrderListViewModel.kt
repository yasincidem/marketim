package com.yasincidem.marketim.features.orderlist

import com.airbnb.mvrx.MvRxViewModelFactory
import com.airbnb.mvrx.ViewModelContext
import com.yasincidem.marketim.core.MvRxViewModel
import com.yasincidem.marketim.network.OrderListService
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject

class OrderListViewModel(
    initialState: OrderListState,
    private val orderListService: OrderListService
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

    companion object : MvRxViewModelFactory<OrderListViewModel, OrderListState> {
        override fun create(viewModelContext: ViewModelContext, state: OrderListState): OrderListViewModel {
            val service: OrderListService by viewModelContext.activity.inject()
            return OrderListViewModel(state, service)
        }
    }
}
