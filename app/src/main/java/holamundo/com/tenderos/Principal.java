package holamundo.com.tenderos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("The Red Store");
        setContentView(R.layout.activity_principal);

    }

    public void startTenderos(View v){
        Intent i = new Intent(Principal.this, Tendero.class);
        startActivity(i);
        finish();
    }
    public void onBackPressed() {

    }
}
