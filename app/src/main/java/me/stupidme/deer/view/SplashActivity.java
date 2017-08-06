package me.stupidme.deer.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import me.stupidme.deer.R;

/**
 * Created by allen on 17-8-5.
 */

public class SplashActivity extends AppCompatActivity {

    CircleImageView mCircleImageView;

    AnimatorSet mAnimatorSet;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        ImageView imageView = (ImageView) findViewById(R.id.image);
        Glide.with(this).asGif().load(R.drawable.deer2).into(imageView);

        mCircleImageView = (CircleImageView) findViewById(R.id.circle_image);
        mCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(
                        SplashActivity.this,
                        mCircleImageView, "avatar")
                        .toBundle();
                startActivity(intent, bundle);
//                overridePendingTransition(0,0);
            }
        });

        ValueAnimator alpha = ObjectAnimator.ofFloat(mCircleImageView, "alpha", 0.7f, 1.0f);
        alpha.setDuration(1500);
        alpha.setRepeatCount(ValueAnimator.INFINITE);
        alpha.setRepeatMode(ValueAnimator.REVERSE);
        ValueAnimator scaleX = ObjectAnimator.ofFloat(mCircleImageView, "scaleX", 1.0f, 1.2f);
        scaleX.setDuration(1500);
        scaleX.setRepeatMode(ValueAnimator.REVERSE);
        scaleX.setRepeatCount(ValueAnimator.INFINITE);
        ValueAnimator scaleY = ObjectAnimator.ofFloat(mCircleImageView, "scaleY", 1.0f, 1.2f);
        scaleY.setDuration(1500);
        scaleY.setRepeatMode(ValueAnimator.REVERSE);
        scaleY.setRepeatCount(ValueAnimator.INFINITE);

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(alpha, scaleX, scaleY);

    }

    @Override
    public void onResume() {
        super.onResume();
        startAnimator();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopAnimator();
    }

    private void startAnimator() {
        mAnimatorSet.start();
    }

    public void stopAnimator() {
        mAnimatorSet.cancel();
    }
}
