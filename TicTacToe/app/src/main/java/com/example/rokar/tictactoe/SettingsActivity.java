package com.example.rokar.tictactoe;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;

/**
 * Created by rokar on 13.06.2016.
 */
public class SettingsActivity extends AppCompatActivity {
    EditText editText1,editText2;
    Spinner spinner1,spinner2;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.menu_activity);
        initAll();
    }

    public  void initAll(){
        sharedPreferences = getSharedPreferences("NAMES",MODE_PRIVATE);
        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        spinner1 = (Spinner) findViewById(R.id.spinner);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        setButtons();
    }
    public void setButtons(){
        String name1, name2;
        long color1,color2;
        try{
            name1 = sharedPreferences.getString("name1","");
            name2 = sharedPreferences.getString("name2","");
            color1 = sharedPreferences.getLong("color1", 0);
            color2 = sharedPreferences.getLong("color2",0);
        }catch (Exception e){
            name1 = name2 = "";
            color1 = color2 = 0;
        }
        editText1.setText(name1);
        editText2.setText(name2);
        spinner1.setSelection((int) color1);
        spinner2.setSelection((int)color2);
    }
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
    public void Cancel(){
        setButtons();
    }
}
