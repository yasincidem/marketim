package com.yasincidem.marketim.core

import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.EpoxyController
import com.airbnb.mvrx.MvRxState
import com.airbnb.mvrx.withState

/**
 * Başka bir thread'de model oluşturmak için.
 */
open class MvRxEpoxyController(
    val buildModelsCallback: EpoxyController.() -> Unit = {}
) : AsyncEpoxyController() {

    override fun buildModels() {
        buildModelsCallback()
    }
}

/**
 * Belirtilen callback ile model oluşturan bir [MvRxEpoxyController] oluşturuyorum ve modelleri build etmesini söylüyorum.
 */
fun BaseEpoxyFragment.simpleController(
    buildModels: EpoxyController.() -> Unit
) = MvRxEpoxyController {
    // view null veya kaldırılıyor ise model build etmemeliyim.
    if (view == null || isRemoving) return@MvRxEpoxyController
    buildModels()
}

/**
 * Belirtilen callback ile model oluşturan bir [MvRxEpoxyController] oluşturuyorum ve
 * bu modelleri belirtilen viewmodel'in o anki durumu ile build etmesini söylüyorum.
 * When models are built the current state of the viewmodel will be provided.
 */

fun <S : MvRxState, A : MvRxViewModel<S>> BaseEpoxyFragment.simpleController(
    viewModel: A,
    buildModels: EpoxyController.(state: S) -> Unit
) = MvRxEpoxyController {
    if (view == null || isRemoving) return@MvRxEpoxyController
    withState(viewModel) { state ->
        buildModels(state)
    }
}
