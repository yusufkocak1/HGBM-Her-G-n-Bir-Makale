package yube.com.hgbmhergnbirmakale.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import yube.com.hgbmhergnbirmakale.R;
import yube.com.hgbmhergnbirmakale.fragments.OneFragment;
import yube.com.hgbmhergnbirmakale.fragments.ThreeFragment;
import yube.com.hgbmhergnbirmakale.fragments.TwoFragment;


public class SimpleTabsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_tabs);
        // getActionBar().setDisplayHomeAsUpEnabled(false);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Her Gün Bir Makale");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "Bilim");
        adapter.addFragment(new TwoFragment(), "Kültür");
        adapter.addFragment(new ThreeFragment(), "Edebiyat");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:

                new jsoup().execute();

        }


        return true;
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    private class jsoup extends AsyncTask<Void, Void, Void> {
        String text_bilim = "İnternet bağlantınız kopmuş yahut kurbağa hızında :(";
        String text_kultur = "İnternet bağlantınız kopmuş yahut kurbağa hızında :(";
        String text_edebiyat = "İnternet bağlantınız kopmuş yahut kurbağa hızında :(";

        boolean temp = true;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {


            try {
                Document doc = Jsoup.connect("http://themusicplayer.org/html/bilim.php").timeout(4000).get();
                Elements info = doc.select("body");

                text_bilim = info.html();
                doc = Jsoup.connect("http://themusicplayer.org/html/kultur.php").timeout(4000).get();
                info = doc.select("body");

                text_kultur = info.html();
                doc = Jsoup.connect("http://themusicplayer.org/html/edebiyat.php").timeout(4000).get();
                info = doc.select("body");

                text_edebiyat = info.html();


                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                // editor.remove("teknoloji");
                editor.putString("bilim", text_bilim);
                editor.putString("kultur", text_kultur);
                editor.putString("edebiyat", text_edebiyat);


                editor.commit();


            } catch (IOException e) {
                e.printStackTrace();
                temp = false;
            }

          //   progressDialog.dismiss();
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            // Set description into TextView
            if (temp) {
                startActivity(new Intent(SimpleTabsActivity.this, SimpleTabsActivity.class));
                finish();
            }
            else
                Toast.makeText(SimpleTabsActivity.this, "İnternet bağlantınız kopmuş yahut kurbağa hızında :(", Toast.LENGTH_SHORT).show();

        }


    }
}
