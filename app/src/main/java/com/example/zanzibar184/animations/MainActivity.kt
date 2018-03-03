import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import com.example.zanzibar184.animations.R
import java.util.*

class MainActivity : AppCompatActivity(), Animation.AnimationListener {

    private lateinit var view: View
    private var viewX = 0f
    private var viewY = 0f
    private var dstX = 0f
    private var dstY = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        view = findViewById(R.id.circle)
        startViewAnimation()
    }

    private fun startViewAnimation(){
        dstX = Random().nextFloat()
        dstY = if (viewY == 1f) 0f else 1f

        val animation = TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, viewX,
                Animation.RELATIVE_TO_PARENT, dstX,
                Animation.RELATIVE_TO_PARENT, viewY,
                Animation.RELATIVE_TO_PARENT, dstY
        )
        animation.duration = 1000L
        animation.setAnimationListener(this)
        animation.fillAfter = true
        view.startAnimation(animation)
    }

    override fun onAnimationEnd(animation: Animation?) {
        viewX = dstX
        viewY = dstY
        startViewAnimation()
    }

    override fun onAnimationStart(animation: Animation?) = Unit
    override fun onAnimationRepeat(animation: Animation?) = Unit

}