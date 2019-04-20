package gr.loginet.loginetloyalty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    /* renamed from: gr.loginet.loginetloyalty.MessageActivity$1 */
    class C04281 implements OnClickListener {
        C04281() {
        }

        public void onClick(View view) {
            MessageActivity.this.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bundle = getIntent().getExtras();
        CharSequence string = bundle.getString("message");
        Integer valueOf = Integer.valueOf(bundle.getInt("image"));
        Integer valueOf2 = Integer.valueOf(bundle.getInt("imageColor"));
        Boolean.valueOf(bundle.getBoolean("runExtraIntent"));
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView((int) C0429R.layout.activity_message);
        Button button = (Button) findViewById(C0429R.id.btn_confirm);
        ImageView imageView = (ImageView) findViewById(C0429R.id.imageViewMessage);
        imageView.setImageResource(valueOf.intValue());
        if (valueOf2 != null) {
            imageView.setColorFilter(valueOf2.intValue());
        }
        ((TextView) findViewById(C0429R.id.textViewMessage)).setText(string);
        button.setOnClickListener(new C04281());
    }
}
