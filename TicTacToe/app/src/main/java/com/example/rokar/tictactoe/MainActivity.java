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
    public int x = 1;
    public char[][] matrix = new char[3][3];
    public Button but1,but2,but3,but4,but5,but6,but7,but8,but9;
    public TextView textView;
    public int[][] winners = new int[3][3];
    public boolean gameover=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        but1 = (Button) findViewById(R.id.button);
        but2 = (Button) findViewById(R.id.button2);
        but3 = (Button) findViewById(R.id.button3);
        but4 = (Button) findViewById(R.id.button4);
        but5 = (Button) findViewById(R.id.button5);
        but6 = (Button) findViewById(R.id.button6);
        but7 = (Button) findViewById(R.id.button7);
        but8 = (Button) findViewById(R.id.button8);
        but9 = (Button) findViewById(R.id.button9);
        but1.setTextColor(Color.BLACK);
        but2.setTextColor(Color.BLACK);
        but3.setTextColor(Color.BLACK);
        but4.setTextColor(Color.BLACK);
        but5.setTextColor(Color.BLACK);
        but6.setTextColor(Color.BLACK);
        but7.setTextColor(Color.BLACK);
        but8.setTextColor(Color.BLACK);
        but9.setTextColor(Color.BLACK);
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
    public void winX(){
        textView.setText("Переміг гравець номер 1!");
        gameover=true;
        drawLineWinner(1);
    }
    public void winO(){
        textView.setText("Переміг гравець номер 1!");
        gameover=true;
        drawLineWinner(2);
    }
    public void drawLineWinner(int win){
        int color = win==1?Color.parseColor("#FFFF0000"):Color.BLUE;
        if(winners[0][0]>0)but1.setTextColor(color);
        if(winners[0][1]>0)but2.setTextColor(color);
        if(winners[0][2]>0)but3.setTextColor(color);
        if(winners[1][0]>0)but4.setTextColor(color);
        if(winners[1][1]>0)but5.setTextColor(color);
        if(winners[1][2]>0)but6.setTextColor(color);
        if(winners[2][0]>0)but7.setTextColor(color);
        if(winners[2][1]>0)but8.setTextColor(color);
        if(winners[2][2]>0)but9.setTextColor(color);
        but1.setEnabled(false);
        but2.setEnabled(false);
        but3.setEnabled(false);
        but4.setEnabled(false);
        but5.setEnabled(false);
        but6.setEnabled(false);
        but7.setEnabled(false);
        but8.setEnabled(false);
        but9.setEnabled(false);
    }
    public void checkIfWin(){
        for(int i = 0 ; i < 3; i++){
            int[] q = new int[2];
            for(int j = 0 ; j < 3; j++)if(matrix[i][j]=='X')q[0]++; else if(matrix[i][j]=='O')q[1]++;
            if(q[0]==3){
                for(int j = 0 ; j < 3; j++)winners[i][j]=1;
                winX();
                return;
            }
            if(q[1]==3){
                for(int j = 0 ; j < 3; j++)winners[i][j]=2;
                winO();
                return;
            }
            q = new int[2];
            for(int j = 0 ; j < 3; j++)if(matrix[j][i]=='X')q[0]++; else if(matrix[j][i]=='O')q[1]++;
            if(q[0]==3){
                for(int j = 0 ; j < 3; j++)winners[j][i]=1;
                winX();
                return;
            }
            if(q[1]==3){
                for(int j = 0 ; j < 3; j++)winners[j][i]=2;
                winO();
                return;
            }
        }
        if(matrix[0][0]==matrix[1][1] && matrix[0][0]==matrix[2][2]){
            if(matrix[0][0]=='X'){
                winners[0][0]=winners[1][1]=winners[2][2]=1;
                winX();
                return ;
            }
            if(matrix[0][0]=='O'){
                winners[0][0]=winners[1][1]=winners[2][2]=2;
                winO();
                return ;
            }
        }
        if(matrix[1][1]==matrix[0][2] && matrix[1][1] == matrix[2][0]){
            if(matrix[0][2]=='X'){
                winners[2][0]=winners[1][1]=winners[0][2]=1;
                winX();
                return ;
            }
            if(matrix[0][2]=='O'){
                winners[2][0]=winners[1][1]=winners[0][2]=2;
                winO();
                return ;
            }
        }
    }
    public void changebut(Button but){
        if(x%2==1){
            but.setText("X");
        }else{
            but.setText("O");
        }
        textView.setText("Хід гравця номер "+(x%2+1));
        x++;
        but.setTextSize(20);
        but.setEnabled(false);
    }
    public void checkIfTie(){
        if(gameover)return;
        boolean ans = false;
        for(int i = 0 ; i < 3; i++)for(int j = 0; j < 3; j++)if(matrix[i][j]!='X' && matrix[i][j]!='O')ans=true;
        if(ans==false){
            textView.setText("НІчия! Перемогла дружба!");
        }
    }
    public void onclick1(View view){
        changebut(but1);
        matrix[0][0]=x%2==0?'X':'O';
        checkIfWin();
        checkIfTie();
    }
    public void onclick2(View view){
        changebut(but2);
        matrix[0][1]=x%2==0?'X':'O';
        checkIfWin();
        checkIfTie();
    }
    public void onclick3(View view){
        changebut(but3);
        matrix[0][2]=x%2==0?'X':'O';
        checkIfWin();
        checkIfTie();
    }
    public void onclick4(View view){
        changebut(but4);
        matrix[1][0]=x%2==0?'X':'O';
        checkIfWin();
        checkIfTie();
    }
    public void onclick5(View view){
        changebut(but5);
        matrix[1][1]=x%2==0?'X':'O';
        checkIfWin();
        checkIfTie();
    }
    public void onclick6(View view){
        changebut(but6);
        matrix[1][2]=x%2==0?'X':'O';
        checkIfWin();
        checkIfTie();
    }
    public void onclick7(View view){
        changebut(but7);
        matrix[2][0]=x%2==0?'X':'O';
        checkIfWin();
        checkIfTie();
    }
    public void onclick8(View view){
        changebut(but8);
        matrix[2][1]=x%2==0?'X':'O';
        checkIfWin();
        checkIfTie();
    }
    public void onclick9(View view){
        changebut(but9);
        matrix[2][2]=x%2==0?'X':'O';
        checkIfWin();
        checkIfTie();
    }
    public void fin(View view){
        finish();
        System.exit(0);
    }
    public void reset(Button but){
        but.setText("");
        but.setEnabled(true);
        but.setTextColor(Color.BLACK);
    }
    public void CLEAR(View view){
        x = 1;
        gameover=false;
        reset(but1);
        reset(but2);
        reset(but3);
        reset(but4);
        reset(but5);
        reset(but6);
        reset(but7);
        reset(but8);
        reset(but9);
        for(int i = 0 ; i < 3; i++)for(int j = 0; j < 3; j++){matrix[i][j]=' ';winners[i][j]=0;}
        textView.setText("Хід гравця номер 1");
    }
}
