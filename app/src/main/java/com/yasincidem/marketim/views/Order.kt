package com.yasincidem.marketim.views

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.yasincidem.marketim.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class Order @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val dateTV: TextView
    private val monthTV: TextView
    private val marketNameTV: TextView
    private val orderNameTV: TextView
    private val productStateTV: TextView
    private val productPriceTV: TextView

    init {
        inflate(context, R.layout.order, this)
        dateTV = findViewById(R.id.date)
        monthTV = findViewById(R.id.month)
        marketNameTV = findViewById(R.id.marketName)
        orderNameTV = findViewById(R.id.orderName)
        productStateTV = findViewById(R.id.productState)
        productPriceTV = findViewById(R.id.productPrice)
        orientation = VERTICAL
    }

    @TextProp
    fun setDate(date: CharSequence) {
        dateTV.text = if (date.length == 1) "0$date" else date
    }

    @TextProp
    fun setMonth(month: CharSequence) {
        monthTV.text = month
    }

    @TextProp
    fun setMarketName(marketName: CharSequence) {
        marketNameTV.text = marketName
    }

    @TextProp
    fun setOrderName(orderName: CharSequence) {
        orderNameTV.text = orderName
    }

    @TextProp
    fun setProductState(productState: CharSequence) {
        productStateTV.text = productState
    }

    @TextProp
    fun setProductPrice(productPrice: CharSequence) {
        productPriceTV.text = productPrice
    }

    @CallbackProp
    override fun setOnClickListener(clickListener: OnClickListener?) {
        super.setOnClickListener(clickListener)
    }
}