package yube.com.hgbmhergnbirmakale.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yube.com.hgbmhergnbirmakale.R;


public class TwoFragment extends Fragment {
    TextView txtdesc = null;
    SharedPreferences preferences;
    public TwoFragment() {
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


        View view=inflater.inflate(R.layout.fragment_two, container, false);
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());

        txtdesc = (TextView) view.findViewById(R.id.text_kultur);
        String text = preferences.getString("kultur", "N/A");
        txtdesc.setText(Html.fromHtml(text, null, null));
        return view;
    }

}
