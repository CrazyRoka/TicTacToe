package com.example.rokar.tictactoe;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    public int counter = 1;
    public char[][] matrix = new char[3][3];
    public TextView textView;
    public Button[][] butMatrix = new Button[3][3];
    public String Name1, Name2;
    public String stringColor1, stringColor2;
    public int color1, color2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defineAllViews();
        setAllColors();
        setTextView();
        SharedPreferences sharedPref =  getSharedPreferences("NAMES", MODE_PRIVATE);
        try{
            int count = 1;
            for(int i = 0; i < 3; i++)for(int j = 0; j < 3; j++){
                matrix[i][j] = sharedPref.getString(String.valueOf(i*3 + j),"").charAt(0);
                if(matrix[i][j]=='X')butMatrix[i][j].setText("X");
                if(matrix[i][j]=='O')butMatrix[i][j].setText("O");
                if(matrix[i][j]=='X' || matrix[i][j]=='O'){
                    butMatrix[i][j].setEnabled(false);
                    count++;
                }
                butMatrix[i][j].setTextSize(20);
            }
            counter = count;
            setTextView();
            checkIfWin();
            checkIfTie();
        }catch (Exception e){

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void initButtonMatrix(){
        butMatrix[0][0] = (Button) findViewById(R.id.button);
        butMatrix[0][1] = (Button) findViewById(R.id.button2);
        butMatrix[0][2]= (Button) findViewById(R.id.button3);
        butMatrix[1][0] = (Button) findViewById(R.id.button4);
        butMatrix[1][1] = (Button) findViewById(R.id.button5);
        butMatrix[1][2] = (Button) findViewById(R.id.button6);
        butMatrix[2][0] = (Button) findViewById(R.id.button7);
        butMatrix[2][1] = (Button) findViewById(R.id.button8);
        butMatrix[2][2] = (Button) findViewById(R.id.button9);
        for(int i = 0; i < 3; i++)for(int j = 0; j < 3; j++)butMatrix[i][j].setTextColor(Color.BLACK);
    }
    public void defineAllViews(){
        stringColor1 = (String)getIntent().getSerializableExtra("Color1");
        stringColor2 = (String)getIntent().getSerializableExtra("Color2");
        Name1 = (String)getIntent().getSerializableExtra("Name1");
        Name2 = (String)getIntent().getSerializableExtra("Name2");
        initButtonMatrix();
        textView = (TextView) findViewById(R.id.textView);
    }
    public void setAllColors(){
        setColor(stringColor1, 1);
        setColor(stringColor2,2);
    }
    public void setColor(String color, int number){
        int createColor = Color.BLACK;
        switch (color){
            case "Червоний":
                createColor = Color.RED;
                break;
            case "Синій":
                createColor = Color.BLUE;
                break;
            case "Жовтий":
                createColor = Color.YELLOW;
                break;
            case "Зелений":
                createColor = Color.GREEN;
                break;
            case "Розовий":
                createColor = Color.parseColor("#FF69B4");
                break;
            default:
                break;
        }
        if(number==1)color1 = createColor;else color2 = createColor;
    }
    public int chooseColor(int color){
        return color==1? color1 : color2;
    }
    public void drawButtons(Button first, Button second, Button third, int color){
        first.setTextColor(chooseColor(color));
        second.setTextColor(chooseColor(color));
        third.setTextColor(chooseColor(color));
    }
    public boolean isLine(char first, char second, char third){
        return first == second && first == third && (first == 'X' || first == 'O');
    }
    public void disableAllButtons(){
        for(int i = 0 ; i < 3; i++)for(int j = 0; j < 3; j++)butMatrix[i][j].setEnabled(false);
    }
    public void checkIfWin(){
        int winner = 0;
        for(int i = 0 ; i < 3; i++){
            if(isLine(matrix[i][0],matrix[i][1],matrix[i][2])){
                winner = matrix[i][0]=='X'? 1 : 2;
                drawButtons(butMatrix[i][0],butMatrix[i][1],butMatrix[i][2],winner);
                break;
            }
            if(isLine(matrix[0][i],matrix[1][i],matrix[2][i])){
                winner = matrix[0][i]=='X'? 1 : 2;
                drawButtons(butMatrix[0][i],butMatrix[1][i],butMatrix[2][i],winner);
                break;
            }
        }
        if(winner == 0 && isLine(matrix[0][0],matrix[1][1],matrix[2][2])){
            winner = matrix[0][0]=='X'? 1 : 2;
            drawButtons(butMatrix[0][0],butMatrix[1][1],butMatrix[2][2],winner);
        }
        if(winner == 0 && isLine(matrix[2][0],matrix[1][1],matrix[0][2])){
            winner = matrix[2][0]=='X'? 1 : 2;
            drawButtons(butMatrix[2][0],butMatrix[1][1],butMatrix[0][2],winner);
        }
        if(winner>0){
            textView.setText("Переміг " + currentPlayerName(winner));
            textView.setTextColor(winner==1? color1: color2);
            disableAllButtons();
        }else{
            checkIfTie();
        }
    }
    public void changeButton(Button but){
        if(counter%2==1){
            but.setText("X");
        }else{
            but.setText("O");
        }
        counter++;
        setTextView();
        but.setTextSize(20);
        but.setEnabled(false);
    }
    public void checkIfTie(){
        boolean ans = false;
        for(int i = 0 ; i < 3; i++)for(int j = 0; j < 3; j++)if(matrix[i][j]!='X' && matrix[i][j]!='O')ans=true;
        if(ans==false){
            textView.setText("НІчия! Перемогла дружба!");
            textView.setTextColor(Color.BLACK);
        }
    }
    public void fillMatrix(Button but){
        for(int i = 0; i < 3; i++)for(int j = 0; j < 3; j++)if(butMatrix[i][j]==but)matrix[i][j]=counter%2==0?'X':'O';
    }
    public void saveData(){
        SharedPreferences.Editor editor = getSharedPreferences("NAMES", MODE_PRIVATE).edit();
        for(int i = 0; i < 3; i++)for(int j = 0; j < 3; j++)editor.putString(String.valueOf(i*3 + j),matrix[i][j]+"ABCD");
        editor.commit();
    }
    public void onClick(View view){
        changeButton((Button)view);
        fillMatrix((Button) view);
        checkIfWin();
        saveData();
    }
    public void Exit(View view){
        finish();
        counter=1;
    }
    public void resetAllButtons(){
        for(int i = 0; i < 3; i++)for(int j = 0; j < 3; j++){Reset(butMatrix[i][j]);matrix[i][j]=' ';}
    }
    public void Reset(Button but){
        but.setText("");
        but.setEnabled(true);
        but.setTextColor(Color.BLACK);
    }
    public String currentPlayerName(int choice){
        return (choice%2==1?Name1:Name2);
    }
    public void setTextView(){
        textView.setText("Хід " + currentPlayerName(counter));
        textView.setTextColor(chooseColor(counter%2));
    }
    public void Clear(View view){
        counter = 1;
        resetAllButtons();
        setTextView();
    }
}
