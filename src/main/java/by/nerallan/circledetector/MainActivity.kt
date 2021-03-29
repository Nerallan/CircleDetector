package by.nerallan.circledetector

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import by.nerallan.circledetector.drawable.CircleDetector

class MainActivity : AppCompatActivity() {

    var imageView: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.image);
        setDrawable()
    }

    private fun setDrawable() {
        val circleDetector = CircleDetector(ContextCompat.getColor(this, R.color.design_default_color_secondary))
        imageView?.setImageDrawable(circleDetector)
    }
}