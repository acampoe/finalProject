package holamundo.com.tenderos;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import holamundo.com.tenderos.Principal;

public class Splash extends AppCompatActivity {
    private static int splash_timeout = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Welcome!");
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash.this, Principal.class);
                startActivity(i);
                finish();
            }
        }, splash_timeout);
    }
}
