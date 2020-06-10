package company.tap.button

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import gotap.com.tapglkitandroid.gl.Views.TapLoadingView


class MainActivity : AppCompatActivity() {

    private lateinit var tapLoadingView: TapLoadingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val text = TextView(this)
        text.text = "OK !"
        text.textSize = 20f
        text.setTextColor(Color.WHITE)
        text.typeface = Typeface.DEFAULT_BOLD
        text.gravity = Gravity.CENTER
        tapLoadingView = TapLoadingView(this)
        tapLoadingView.useCustomColor = true;
        tapLoadingView.color = ContextCompat.getColor(this, android.R.color.white);
//        tapLoadingView.setPercent(1.0f)

        val imageView = ImageView(this)
        imageView.setImageResource(R.drawable.ic_circle_checck)
//        loading_button.addView(imageView)

    }

    fun startAnimation(view: View) {
        tapLoadingView.start()
        (view as LoadingButton).startAnimation()
    }
}