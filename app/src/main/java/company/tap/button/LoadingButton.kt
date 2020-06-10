package company.tap.button

import android.animation.*
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat

/**
 * Created by Mario Gamal on 6/9/20
 * Copyright © 2020 Tap Payments. All rights reserved.
 */
class LoadingButton : CardView {
    var mGradientDrawable: GradientDrawable? = null
    var mMorphingAnimatorSet: AnimatorSet? = null
    var mIsMorphingInProgress = false
    var mState = State.IDLE


    enum class State {
        PROGRESS, IDLE
    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        mGradientDrawable = ContextCompat.getDrawable(context, R.drawable.shape_default) as GradientDrawable?
        background = mGradientDrawable
    }

    fun startAnimation() {
        if (mState != State.IDLE) {
            return
        }

        val initialWidth = width
        val initialHeight = height
        val initialCornerRadius = 100
        val finalCornerRadius = 1000
        mState = State.PROGRESS
        mIsMorphingInProgress = true
        isClickable = false
        val toWidth = 150

        val cornerAnimation = ObjectAnimator.ofFloat(mGradientDrawable,
                "cornerRadius",
                initialCornerRadius.toFloat(),
                finalCornerRadius.toFloat())

        val widthAnimation = ValueAnimator.ofInt(initialWidth, toWidth)
        widthAnimation.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            val layoutParams = layoutParams
            layoutParams.width = `val`
            setLayoutParams(layoutParams)
        }

        val heightAnimation = ValueAnimator.ofInt(initialHeight, toWidth)
        heightAnimation.addUpdateListener { valueAnimator ->
            val `val` = valueAnimator.animatedValue as Int
            val layoutParams = layoutParams
            layoutParams.height = `val`
            setLayoutParams(layoutParams)
        }

        mMorphingAnimatorSet = AnimatorSet()
        mMorphingAnimatorSet!!.duration = 1000
        mMorphingAnimatorSet!!.playTogether(cornerAnimation, widthAnimation, heightAnimation)
        mMorphingAnimatorSet!!.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                mIsMorphingInProgress = false

                val imageView = ImageView(context)
                imageView.setImageResource(R.drawable.ic_circle_checck)
                addView(imageView)
            }
        })
        mMorphingAnimatorSet!!.start()
    }
}