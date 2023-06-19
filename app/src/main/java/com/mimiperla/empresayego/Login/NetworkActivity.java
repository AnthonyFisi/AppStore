package com.mimiperla.empresayego.Login;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.mimiperla.empresayego.R;

import androidx.appcompat.app.AppCompatActivity;

public class NetworkActivity extends AppCompatActivity {

    private LinearLayout load_again;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        load_again=findViewById(R.id.load_again);
        load_again.setOnClickListener( v->{
            Intent intent= new Intent(getApplicationContext(), SplashActivity.class);
            startActivity(intent);
            finish();
        });

    }


}
