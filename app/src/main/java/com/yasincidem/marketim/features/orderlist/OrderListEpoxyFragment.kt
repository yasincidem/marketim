package com.yasincidem.marketim.features.orderlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import com.airbnb.mvrx.fragmentViewModel
import com.yasincidem.marketim.R
import com.yasincidem.marketim.core.BaseEpoxyFragment
import com.yasincidem.marketim.core.simpleController
import com.yasincidem.marketim.views.order
import com.yasincidem.marketim.views.orderDetail


class OrderListEpoxyFragment : BaseEpoxyFragment() {
    private val orderListViewModel: OrderListViewModel by fragmentViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.my_orders_action_bar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.log_out -> {
                context?.let { AlertDialog.Builder(it) }!!
                    .setMessage(getString(R.string.log_out_alert_warning_message))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.log_out_alert_warning_yes)) { _, _ ->
                        orderListViewModel.setIfUserWillRemembered(value = false)
                        view?.findNavController()?.navigate(R.id.action_main_to_login_page_nav)
                    }
                    .setNegativeButton(getString(R.string.log_out_alert_warning_no)) { dialog, _ -> dialog.cancel()
                    }.create().show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun epoxyController() = simpleController(orderListViewModel) { state ->
        state.orders()?.forEachIndexed { index, order ->
            order {
                id("order$index")
                date(order.date.toString())
                month(resources.getStringArray(R.array.aylar)[order.month - 1])
                marketName((order.marketName))
                orderName(order.orderName)
                productState(order.productState)
                productPrice(order.productPrice.toString())
                productStateIcon(order.productState)
                onOrderExpanded {
                    _ ->
                    run {
                        order.isExpanded = !order.isExpanded
                        orderListViewModel.requestExpand()
                    }
                }
            }
            if (order.isExpanded) {
                orderDetail {
                    id("orderDetail$index")
                    orderDetail(order.productDetail.orderDetail)
                    summaryPrice(order.productDetail.summaryPrice.toString())
                    backgroundColor(order.productState)
                }
            }
        }

    }
}