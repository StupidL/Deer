package me.stupidme.deer.view;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import me.stupidme.deer.R;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);

        final FloatingCountDownView floatingCountDownView = (FloatingCountDownView) findViewById(R.id.count_down_view);
        floatingCountDownView.setCountDownTimeInSeconds(10);
        floatingCountDownView.setOnCountDownFinishListener(new FloatingCountDownView.OnCountDownFinishListener() {
            @Override
            public void onFinished() {
                Toast.makeText(DetailActivity.this, "Finish!", Toast.LENGTH_LONG).show();
            }
        });
        floatingCountDownView.setCircleBackgroundColor(Color.CYAN);
        floatingCountDownView.setArcColor(Color.GRAY);
        floatingCountDownView.start();

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
    }

}
