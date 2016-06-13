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
    EditText editText1;
    EditText editText2;
    Spinner spinner1, spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_activity);
        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        SharedPreferences sharedPref =  getSharedPreferences("NAMES", MODE_PRIVATE);
        String text = sharedPref.getString("name1",null);
        if(text!=null){
            String name1, name2;
            long color1,color2;
            try {
                name1 = sharedPref.getString("name1", "");
                name2 = sharedPref.getString("name2", "");
                color1 = sharedPref.getLong("color1", 0);
                color2 = sharedPref.getLong("color2", 0);
            }catch (Exception e){
                color1 = color2 = -1;
                name1 = name2 = "";
            }
            editText1.setText(name1);
            editText2.setText(name2);
            spinner1.setSelection((int)color1);
            spinner2.setSelection((int)color2);
        }
    }
    private boolean isName(String a){
        boolean ans = false;
        for(int i = 0; i < a.length(); i++)if(a.charAt(i)!=' ')ans=true;
        return ans;
    }
    public String getColor(Spinner spinner){return String.valueOf(spinner.getSelectedItem());}
    public void saveData(){
        String name1 = editText1.getText().toString();
        String name2 = editText2.getText().toString();
        SharedPreferences.Editor editor = getSharedPreferences("NAMES", MODE_PRIVATE).edit();
        long color1 = spinner1.getSelectedItemId();
        long color2 = spinner2.getSelectedItemId();
        editor.putString("name1", name1);
        editor.putString("name2", name2);
        editor.putLong("color1", color1);
        editor.putLong("color2", color2);
        editor.commit();
    }
    public void startGame(){
        Intent intent = new Intent(this,GameActivity.class);
        String name1 = editText1.getText().toString();
        String name2 = editText2.getText().toString();
        intent.putExtra("Name1", name1);
        intent.putExtra("Name2", name2);
        intent.putExtra("Color1", getColor(spinner1));
        intent.putExtra("Color2",getColor(spinner2));
        saveData();
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
    public void Start(View view){
        if(isName(editText1.getText().toString()) && isName(editText2.getText().toString())) {
            if(spinner1.getSelectedItem().toString()==spinner2.getSelectedItem().toString()){
                showDialog("Введіть різні кольори гравцям!");
            }else {
                startGame();
            }
        }else{
            showDialog("Введіть імена гравцям корректно!");
        }
    }
    public void ExitApp(View view){
        finish();
    }
}
