package nyc.c4q.wesniemarcelin.resourcedapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import nyc.c4q.wesniemarcelin.resourcedapp.R
import android.content.Intent
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import nyc.c4q.wesniemarcelin.resourcedapp.MainActivity

/**
 * Created by wesniemarcelin on 2/2/17.
 */
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen_layout)
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_hyperspace_jump)
        val imageView = findViewById<View>(R.id.books) as ImageView
        imageView.animation = animation

//        ChildCareClient.connectToServer(ChildCareClient.getBaseUrl());
//        UPKClient.connectToServer(UPKClient.getBaseUrl());

//        ImageView image = (ImageView) findViewById(R.id.imageView);
//        Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.hyperspacejump);
//        image.startAnimation(hyperspaceJump);
        val handler = Handler()
        handler.postDelayed({
            startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
            finish()
            //
        }, 6000)
    }
}