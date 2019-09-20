package com.yasincidem.marketim.features.orderlist

import android.os.Bundle
import com.airbnb.mvrx.fragmentViewModel
import com.yasincidem.marketim.R
import com.yasincidem.marketim.core.BaseEpoxyFragment
import com.yasincidem.marketim.core.simpleController
import com.yasincidem.marketim.views.order
import com.yasincidem.marketim.views.orderDetail


class OrderListEpoxyFragment : BaseEpoxyFragment() {
    private val viewModel: OrderListViewModel by fragmentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = getString(R.string.order_list_fragment_title)
    }
    override fun epoxyController() = simpleController(viewModel) { state ->
        state.orders()?.forEachIndexed { index, order ->
            order {
                id("order$index")
                date(order.date.toString())
                month(resources.getStringArray(R.array.aylar)[order.month - 1])
                marketName((order.marketName))
                orderName(order.orderName)
                productState(order.productState)
                productPrice(order.productPrice.toString())
                onOrderExpanded {
                    _ ->
                    run {
                        order.isExpanded = !order.isExpanded
                        viewModel.requestExpand()
                    }
                }
            }
            if (order.isExpanded) {
                orderDetail {
                    id("orderDetail$index")
                    orderDetail(order.productDetail.orderDetail)
                    summaryPrice(order.productDetail.summaryPrice.toString())
                }
            }
        }
    }
}