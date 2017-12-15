package yube.com.hgbmhergnbirmakale.fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yube.com.hgbmhergnbirmakale.R;



public class ThreeFragment extends Fragment{
    TextView txtdesc = null;
    SharedPreferences preferences;
    public ThreeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

        txtdesc=(TextView) view.findViewById(R.id.text_edebiyat);
        String text = preferences.getString("edebiyat", "N/A");
        txtdesc.setText(Html.fromHtml(text, null, null));
       // new text().execute();

        return view;
    }
    private class text extends AsyncTask<Void, Void, Void> {
        String text = "df";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {


            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            // Set description into TextView

            String text = preferences.getString("edebiyat", "N/A");
            txtdesc.setText(Html.fromHtml(text, null, null));


        }

    }


}

