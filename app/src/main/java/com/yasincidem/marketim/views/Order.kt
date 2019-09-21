package com.yasincidem.marketim.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
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

    private val dateTV by lazy { findViewById<TextView>(R.id.date) }
    private val monthTV by lazy { findViewById<TextView>(R.id.month) }
    private val marketNameTV by lazy { findViewById<TextView>(R.id.marketName) }
    private val orderNameTV by lazy { findViewById<TextView>(R.id.orderName) }
    private val productStateTV by lazy { findViewById<TextView>(R.id.productState) }
    private val productPriceTV by lazy { findViewById<TextView>(R.id.productPrice) }
    private val productStateIconIV by lazy { findViewById<ImageView>(R.id.product_state_icon) }

    init {
        inflate(context, R.layout.order, this)
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
        productStateTV.setTextColor(ContextCompat.getColor(context,
            when(productState) {
                "Yolda" -> R.color.cargo
                "Hazırlanıyor" -> R.color.preparing
                "Onay Bekliyor" -> R.color.waiting_for_approve
                else -> android.R.color.darker_gray
            }
        ))
    }


    @TextProp
    fun setProductStateIcon(productState: CharSequence) {
        productStateIconIV.setImageResource(
            when(productState) {
                "Yolda" -> R.mipmap.ic_action_tracking_1
                "Hazırlanıyor" -> R.mipmap.ic_action_stopwatch
                "Onay Bekliyor" -> R.mipmap.ic_action_validation_1
                else -> android.R.mipmap.sym_def_app_icon
            }
        )
    }

    @TextProp
    fun setProductPrice(productPrice: CharSequence) {
        productPriceTV.text = productPrice
    }

    @CallbackProp
    fun setOnOrderExpanded(clickListener: OnClickListener?) {
        setOnClickListener(clickListener)
    }
}