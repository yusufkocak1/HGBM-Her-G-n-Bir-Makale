package yube.com.hgbmhergnbirmakale.alertdialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import yube.com.hgbmhergnbirmakale.R;
import yube.com.hgbmhergnbirmakale.activity.SimpleTabsActivity;

public class alertactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alertactivity);
        Intent i = getIntent();
        String message=i.getExtras().getString("message");
        new alert().showalert(this,message);

    }



    public class alert {


        public void showalert(final Activity activity, String notificate_text) {
            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.alert);
            final TextView notificate = (TextView) dialog.findViewById(R.id.text_dialog);
            AppCompatButton cancel_btn = (AppCompatButton) dialog.findViewById(R.id.cancel_btn_dialog);
            AppCompatButton copy_btn = (AppCompatButton) dialog.findViewById(R.id.copy_btn_dialog);
            notificate.setText(notificate_text);

            cancel_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, SimpleTabsActivity.class);
                    finish();
                    startActivity(intent);
                }
            });

            copy_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.content.ClipboardManager myClipboard = (android.content.ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    String text;
                    text = notificate.getText().toString();

                    ClipData myClip = ClipData.newPlainText("text", text);
                    myClipboard.setPrimaryClip(myClip);

                    Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();
                }
            });

            dialog.show();
        }
    }
}
