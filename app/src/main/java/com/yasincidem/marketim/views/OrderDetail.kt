package com.yasincidem.marketim.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
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

    @TextProp
    fun setBackgroundColor(productState: CharSequence) {
        setBackgroundColor(Color.parseColor(
            "#" + Integer.toHexString(ContextCompat.getColor(context,
                when(productState) {
                    "Yolda" -> R.color.cargo
                    "Hazırlanıyor" -> R.color.preparing
                    "Onay Bekliyor" -> R.color.waiting_for_approve
                    else -> android.R.color.darker_gray
                }
            ) and 0x00ffffff))
        )
    }
}