package nyc.c4q.wesniemarcelin.resourcedapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import nyc.c4q.wesniemarcelin.resourcedapp.backend.ChildCareClient;
import nyc.c4q.wesniemarcelin.resourcedapp.backend.UPKClient;

/**
 * Created by wesniemarcelin on 2/2/17.
 */

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_hyperspace_jump);
        ImageView imageView = (ImageView) findViewById(R.id.books);
        imageView.setAnimation(animation);

        ChildCareClient.connectToServer(ChildCareClient.getBaseUrl());
        UPKClient.connectToServer(UPKClient.getBaseUrl());

//        ImageView image = (ImageView) findViewById(R.id.imageView);
//        Animation hyperspaceJump = AnimationUtils.loadAnimation(this, R.anim.hyperspacejump);
//        image.startAnimation(hyperspaceJump);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
//
            }
        }, 6000);


    }
}
