package me.stupidme.deer.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import me.stupidme.deer.R;
import me.stupidme.deer.RecordItemView;
import me.stupidme.deer.model.RecordItem;
import me.stupidme.deer.presenter.RecordPresenter;
import me.stupidme.deer.presenter.RecordPresenterImpl;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton mFab;

    private RecordPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,DetailActivity.class));
            }
        });

        mPresenter = new RecordPresenterImpl(new RecordItemView() {
            @Override
            public void addItem(RecordItem item) {

            }

            @Override
            public void removeItem(RecordItem item) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
//        mPresenter.loadRecords();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            MainActivity.this.finish();
            return true;
        }

        if (id == android.R.id.home) {
            mFab.setVisibility(View.GONE);
            supportFinishAfterTransition();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        mFab.setVisibility(View.GONE);
        supportFinishAfterTransition();
    }
}
