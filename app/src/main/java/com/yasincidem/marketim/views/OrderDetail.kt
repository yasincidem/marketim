package com.yasincidem.marketim.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.yasincidem.marketim.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class OrderDetail @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val orderDetailTV by lazy { findViewById<TextView>(R.id.orderDetail) }
    private val summaryPriceTV by lazy { findViewById<TextView>(R.id.summaryPrice) }

    init {
        inflate(context, R.layout.fragment_order_detail, this)
        orientation = VERTICAL
    }

    @TextProp
    fun setOrderDetail(orderDetail: CharSequence) {
        orderDetailTV.text = orderDetail
    }

    @TextProp
    fun setSummaryPrice(summaryPrice: CharSequence) {
        summaryPriceTV.text = summaryPrice
    }

}