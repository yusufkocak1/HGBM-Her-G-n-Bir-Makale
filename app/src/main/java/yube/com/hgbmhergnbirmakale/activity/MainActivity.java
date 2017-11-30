package yube.com.hgbmhergnbirmakale.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import yube.com.hgbmhergnbirmakale.R;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private Button btnSimpleTabs, btnScrollableTabs, btnIconTextTabs, btnIconTabs, btnCustomIconTextTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent(MainActivity.this, SimpleTabsActivity.class));



    }



}
