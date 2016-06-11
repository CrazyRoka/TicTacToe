package com.example.rokar.tictactoe;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public int counter = 1;
    public char[][] matrix = new char[3][3];
    public TextView textView;
    public Button[][] butMatrix = new Button[3][3];
    public String Name1, Name2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name1 = (String)getIntent().getSerializableExtra("Name1");
        Name2 = (String)getIntent().getSerializableExtra("Name2");
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
        textView = (TextView) findViewById(R.id.textView);
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
    public void drawButtons(Button first, Button second, Button third, int color){
        first.setTextColor(color==1?Color.RED : Color.BLUE);
        second.setTextColor(color==1?Color.RED : Color.BLUE);
        third.setTextColor(color==1?Color.RED : Color.BLUE);
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
            textView.setText("Переміг " + (winner==1?Name1 : Name2));
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
        }
    }
    public void fillMatrix(Button but){
        for(int i = 0; i < 3; i++)for(int j = 0; j < 3; j++)if(butMatrix[i][j]==but)matrix[i][j]=counter%2==0?'X':'O';
    }
    public void onClick(View view){
        changeButton((Button)view);
        fillMatrix((Button)view);
        checkIfWin();
    }
    public void Exit(View view){
        finish();
        resetAllButtons();
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
    public void setTextView(){
        textView.setText("Хід " + (counter%2==1?Name1:Name2));
    }
    public void Clear(View view){
        counter = 1;
        resetAllButtons();
        setTextView();
    }
}
