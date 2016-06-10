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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void checkIfWin(){
        int winner = 0;
        for(int i = 0 ; i < 3; i++){
            int[] q = new int[2];
            for(int j = 0 ; j < 3; j++)if(matrix[i][j]=='X')q[0]++; else if(matrix[i][j]=='O')q[1]++;
            if(q[0]==3){
                for(int j = 0 ; j < 3; j++)butMatrix[i][j].setTextColor(Color.RED);
                winner = 1;
                break;
            }
            if(q[1]==3){
                for(int j = 0 ; j < 3; j++)butMatrix[i][j].setTextColor(Color.BLUE);
                winner = 2;
                break;
            }
            q = new int[2];
            for(int j = 0 ; j < 3; j++)if(matrix[j][i]=='X')q[0]++; else if(matrix[j][i]=='O')q[1]++;
            if(q[0]==3){
                for(int j = 0 ; j < 3; j++)butMatrix[j][i].setTextColor(Color.RED);
                winner = 1;
                break;
            }
            if(q[1]==3){
                for(int j = 0 ; j < 3; j++)butMatrix[j][i].setTextColor(Color.BLUE);
                winner = 2;
                break;
            }
        }
        if(winner == 0 && matrix[0][0]==matrix[1][1] && matrix[0][0]==matrix[2][2]){
            if(matrix[0][0]=='X'){
                for(int i = 0; i < 3; i++)butMatrix[i][i].setTextColor(Color.RED);
                winner = 1;
            }
            if(matrix[0][0]=='O'){
                for(int i = 0; i < 3; i++)butMatrix[i][i].setTextColor(Color.BLUE);
                winner = 2;
            }
        }
        if(winner == 0 && matrix[1][1]==matrix[0][2] && matrix[1][1] == matrix[2][0]){
            if(matrix[0][2]=='X'){
                for(int i = 2, j = 0; j < 3; i--, j++)butMatrix[i][j].setTextColor(Color.RED);
                winner = 1;
            }
            if(matrix[0][2]=='O'){
                for(int i = 2, j = 0; j < 3; i--, j++)butMatrix[i][j].setTextColor(Color.BLUE);
                winner = 1;
            }
        }
        if(winner>0){
            textView.setText("Переміг гравець номер " + Integer.toString(winner) + "!");
            for(int i = 0; i < 3; i++)for(int j = 0; j < 3; j++)butMatrix[i][j].setEnabled(false);
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
        textView.setText("Хід гравця номер "+(counter++%2+1));
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
    public void onClick(View view){
        changeButton((Button)view);
        for(int i = 0; i < 3; i++)for(int j = 0; j < 3; j++)if(butMatrix[i][j]==(Button)view)matrix[i][j]=counter%2==0?'X':'O';
        checkIfWin();
    }
    public void Exit(View view){
        finish();
        System.exit(0);
    }
    public void Reset(Button but){
        but.setText("");
        but.setEnabled(true);
        but.setTextColor(Color.BLACK);
    }
    public void Clear(View view){
        counter = 1;
        for(int i = 0; i < 3; i++)for(int j = 0; j < 3; j++){Reset(butMatrix[i][j]);matrix[i][j]=' ';}
        textView.setText("Хід гравця номер 1");
    }
}
