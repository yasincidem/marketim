package com.yasincidem.marketim.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyRecyclerView
import com.airbnb.mvrx.BaseMvRxFragment
import com.yasincidem.marketim.R

abstract class BaseEpoxyFragment : BaseMvRxFragment() {

    private lateinit var recyclerView: EpoxyRecyclerView
    protected val epoxyController by lazy { epoxyController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        epoxyController.onRestoreInstanceState(savedInstanceState)
    }

    /** Sıradan onCreateView mantığı fakat burada önemli kısım recyclerview'e epoxyContoller'ı set etmek gerekiyor. */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base_mvrx, container, false).apply {
            recyclerView = findViewById(R.id.recycler_view)
            recyclerView.setController(epoxyController)
        }
    }

    /** invalidate() metodu React'ın render fonksiyonuna karşılık geliyor. Recyclerview'in içini modellerle doldurmasını istiyorum.*/
    override fun invalidate() {
        recyclerView.requestModelBuild()
    }

     /** Bu fragment için modeller üretileceği için MvRxEpoxyController objesi üzerinden işlem yapılıyor */
    abstract fun epoxyController(): MvRxEpoxyController

    /**  Cihazın oryantasyon değişikliğini gibi olalarda epoxy'nin anlaması ve değerleri koruması için current state'i epoxyContoller'a bildirmek gerekiyor */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        epoxyController.onSaveInstanceState(outState)
    }

    /**  View destroy olduğunda epoxyController'ın model build etmesini bitirmesi için cancelPendingModelBuild() ile epoxyContoller'a haber veriyorum */
    override fun onDestroyView() {
        epoxyController.cancelPendingModelBuild()
        super.onDestroyView()
    }
}
