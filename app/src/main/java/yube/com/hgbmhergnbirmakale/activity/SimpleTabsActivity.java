package yube.com.hgbmhergnbirmakale.activity;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.widget.TextView;

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

        new text().execute();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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


    private class text extends AsyncTask<Void, Void, Void> {
        String text;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect("http://themusicplayer.org/html/hgbm.php").get();
                Elements info = doc.select("body");

                text=info.html();

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;

        }
        @Override
        protected void onPostExecute(Void result) {
            // Set description into TextView
            TextView txtdesc = (TextView) findViewById(R.id.text);
            txtdesc.setText(Html.fromHtml(text, new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String source) {
                    return null;
                }
            }, null));

        }


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
}
