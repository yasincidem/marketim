package com.yasincidem.marketim.features.orderlist

import com.airbnb.mvrx.Async
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.Uninitialized
import com.yasincidem.marketim.models.Order

data class OrderListState(
    val orders: Async<List<Order>> = Uninitialized,
    val isExpanded: Boolean = false
) : MvRxState
