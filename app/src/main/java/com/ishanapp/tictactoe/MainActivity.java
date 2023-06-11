package com.ishanapp.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    int activePlayer = 0;
    // 0 == Red and 1 == Green

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // 2 means unplayed
    boolean gameIsActive = true;
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    public void DropIn(View view){
        ImageView counter = (ImageView) view;

        int tappedCount = Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCount] == 2 && gameIsActive) {
            gameState[tappedCount] = activePlayer;
            counter.setTranslationY(-1600f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.redcoin);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.greencoin);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1600f).rotation(360).setDuration(300);

            for(int[] winningPosition : winningPositions){
                if(gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 2){

                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);
                    String winner = "Red";
                    gameIsActive = false;

                    if(gameState[winningPosition[0]] == 1){
                        winner = "Green";
                        layout.setBackgroundColor(Color.parseColor("#98FF98"));
                    }
                    else{
                        winner = "Red";
                        layout.setBackgroundColor(Color.parseColor("#D96C6C"));
                    }

                    TextView winnerMessage = (TextView) findViewById(R.id.WinnerMessage);
                    winnerMessage.setText(String.format("%s has won!!", winner));



                }
                else{
                    boolean gameOver = true;
                    for(int count : gameState){
                        if(count == 2){
                            gameOver = false;
                        }
                    }
                    if(gameOver){
                        TextView winnerMessage = (TextView) findViewById(R.id.WinnerMessage);
                        winnerMessage.setText("It's a Draw");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(View.VISIBLE);
                        layout.setBackgroundColor(Color.parseColor("#87CEEB"));
                    }
                }
            }
        }
    }

    public void playAgain(View view){

        Button playAgainButton = findViewById(R.id.playAgainButton);
        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                layout.setVisibility(View.INVISIBLE);
                Arrays.fill(gameState, 2);
                activePlayer = 0;
                gameIsActive = true;

                GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
                for(int i = 0; i < gridLayout.getChildCount(); i++){
                    ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
                }
            }
        });

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}