package com.rafalzawadzki.github.core.misc


import android.view.View
import android.view.View.*

/**
 * Sets the visibility to [View.GONE] on this View
 *
 * @param invisible if true, the View will be set to [View.INVISIBLE]
 */
fun View.hide(invisible: Boolean = false) { visibility = if (invisible) INVISIBLE else GONE }

/**
 * Sets the visibility to [View.VISIBLE] on this View
 *
 * @param show if set to false the visibility will be set to [View.GONE]
 */
fun View.show(show: Boolean = true) { visibility = if (show) VISIBLE else GONE }

