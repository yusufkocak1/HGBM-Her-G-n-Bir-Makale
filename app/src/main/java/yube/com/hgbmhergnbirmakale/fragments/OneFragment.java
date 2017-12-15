package yube.com.hgbmhergnbirmakale.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import yube.com.hgbmhergnbirmakale.R;
import yube.com.hgbmhergnbirmakale.activity.SimpleTabsActivity;


@SuppressLint("ValidFragment")
public class OneFragment extends Fragment {
     TextView txtdesc = null;
    SharedPreferences preferences;

    @SuppressLint("ValidFragment")
    public OneFragment() {
   }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view=inflater.inflate(R.layout.fragment_one, container, false);
         preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());



        txtdesc = (TextView) view.findViewById(R.id.text);

        String text = preferences.getString("bilim", "N/A");
        txtdesc.setText(Html.fromHtml(text, null, null));

        // Inflate the layout for this fragment
        return view;



    }

}
