package com.example.revoluttestapp.currencyconverter.view.animations

import android.view.View

internal fun View.animateAppearanceWithAlphaIfItIsHidden(){
    if(alpha != 1f && visibility!= View.VISIBLE){
        visibility = View.VISIBLE
        alpha = 0f
        animate()
            .alpha(1f)
            .setDuration(300)
            .start()
    }
}