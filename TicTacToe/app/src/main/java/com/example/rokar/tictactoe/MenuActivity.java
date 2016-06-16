package com.example.rokar.tictactoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by rokar on 11.06.2016.
 */
public class MenuActivity extends Activity {
    String Name1,Name2;
    long Color1, Color2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_activity);
        loadData();
    }
    void loadData(){
        SharedPreferences sharedPref =  getSharedPreferences("NAMES", MODE_PRIVATE);
        if(sharedPref.contains("name1"))Name1 = sharedPref.getString("name1", ""); else Name1 = "";
        if(sharedPref.contains("name2"))Name2 = sharedPref.getString("name2", ""); else Name2 = "";
        if(sharedPref.contains("color1"))Color1 = sharedPref.getLong("color1", 0); else Color1 = -1;
        if(sharedPref.contains("color2"))Color2 = sharedPref.getLong("color2", 0); else Color2 = -1;
    }
    private boolean isName(String a){
        boolean ans = false;
        for (int i = 0; i < a.length(); i++) if (a.charAt(i) != ' ') ans = true;
        return ans;
    }
    private String decodeColor(long value){return value==0? "Червоний" : value == 1? "Синій" : value == 2? "Жовтий" : value == 3? "Зелений" : "Розовий";}
    public void startGame(){
        Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra("Name1", Name1);
        intent.putExtra("Name2", Name2);
        intent.putExtra("Color1", decodeColor(Color1));
        intent.putExtra("Color2", decodeColor(Color2));
        startActivity(intent);
    }
    public void openOptions(View view){
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }
    public void showDialog(String message){
        new AlertDialog.Builder(this)
                .setTitle("Помилка")
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
    public boolean validateColors(){return Color1==Color2;}
    public boolean validateNames(){return isName(Name1) && isName(Name2);}
    public void Start(View view){
        if(validateNames()) {
            if(validateColors()){
                showDialog("Введіть різні кольори гравцям у налаштуваннях!");
            }else {
                startGame();
            }
        }else{
            showDialog("Введіть імена гравцям у налаштуваннях!");
        }
    }
    public void ExitApp(View view){
        finish();
    }
}
