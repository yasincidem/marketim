package com.yasincidem.marketim.features.orderlist

import com.airbnb.mvrx.fragmentViewModel
import com.yasincidem.marketim.R
import com.yasincidem.marketim.core.BaseEpoxyFragment
import com.yasincidem.marketim.core.simpleController
import com.yasincidem.marketim.views.order


class OrderListEpoxyFragment : BaseEpoxyFragment() {
    private val viewModel: OrderListViewModel by fragmentViewModel()

    override fun epoxyController() = simpleController(viewModel) { state ->
        state.orders()?.forEach { order ->
            order {
                id(order.date)
                date(order.date.toString())
                month(resources.getStringArray(R.array.aylar)[order.month - 1])
                marketName((order.marketName))
                orderName(order.orderName)
                productState(order.productState)
                productPrice(order.productPrice.toString())
            }
        }
    }
}