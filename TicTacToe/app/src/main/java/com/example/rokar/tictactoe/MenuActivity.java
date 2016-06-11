package com.example.rokar.tictactoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by rokar on 11.06.2016.
 */
public class MenuActivity extends Activity {
    EditText editText1;
    EditText editText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_activity);
        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
    }
    private boolean isName(String a){
        boolean ans = false;
        for(int i = 0; i < a.length(); i++)if(a.charAt(i)!=' ')ans=true;
        return ans;
    }
    public void newActivity(){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("Name1",editText1.getText().toString());
        intent.putExtra("Name2",editText2.getText().toString());
        startActivity(intent);
    }
    public void showDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Помилка")
                .setMessage("Введіть назви гравців корректно!")
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
            newActivity();
        }else{
            showDialog();
        }
    }
    public void ExitApp(View view){
        finish();
    }
}
