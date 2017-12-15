package yube.com.hgbmhergnbirmakale.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import yube.com.hgbmhergnbirmakale.R;


public class MainActivity extends AppCompatActivity {


    ProgressBar mprogressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Animation anim1 = AnimationUtils.loadAnimation(this,R.anim.anim_down);
        ImageView img =(ImageView)findViewById(R.id.imageView);
        img.setAnimation(anim1);
        new jsoup().execute();

        mprogressBar = (ProgressBar) findViewById(R.id.progressBar);
        ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
        anim.setDuration(4000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();


    }



    private class jsoup extends AsyncTask<Void, Void, Void> {
        String text_bilim="İnternet bağlantınız kopmuş yahut kurbağa hızında :(";
        String text_kultur="İnternet bağlantınız kopmuş yahut kurbağa hızında :(";
        String text_edebiyat="İnternet bağlantınız kopmuş yahut kurbağa hızında :(";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect("http://themusicplayer.org/html/bilim.php").timeout(5000).get();
                Elements info = doc.select("body");

                text_bilim=info.html();
                doc = Jsoup.connect("http://themusicplayer.org/html/kultur.php").timeout(5000).get();
                info = doc.select("body");

                text_kultur=info.html();
                doc = Jsoup.connect("http://themusicplayer.org/html/edebiyat.php").timeout(5000).get();
                info = doc.select("body");

                text_edebiyat=info.html();


                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
                // editor.remove("teknoloji");
                editor.putString("bilim", text_bilim);
                editor.putString("kultur", text_kultur);
                editor.putString("edebiyat", text_edebiyat);



                editor.commit();



            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;

        }
        @Override
        protected void onPostExecute(Void result) {
            // Set description into TextView
            startActivity(new Intent(MainActivity.this,SimpleTabsActivity.class));
            finish();

             }



    }

}