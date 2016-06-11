package com.example.rokar.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by rokar on 11.06.2016.
 */
public class MenuActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_activity);
    }
    public void Start(View view){
        Button button = (Button) view;
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
