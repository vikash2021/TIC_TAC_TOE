package com.vkk.demo_game;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.GridLayout;
import android.content.Intent;





public class MainActivity extends AppCompatActivity {


    //0=cross,1=zero;
    int activePlayer=1;
    boolean gameIsActive=true;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][]winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void DropIn(View view)
    {
        ImageView counter = (ImageView) view;
        System.out.println(counter.getTag().toString());
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedCounter] == 2&& gameIsActive) {

            gamestate[tappedCounter]=activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 1) {

                counter.setImageResource(R.drawable.lettero);
                activePlayer = 0;
            } else {
                counter.setImageResource(R.drawable.letterx);
                activePlayer = 1;

            }

            counter.animate().translationYBy(1000f).setDuration(500);
            for(int[] winningPosition:winningPositions)
            {
              if(gamestate[winningPosition[0]]==gamestate[winningPosition[1]]&&
                      gamestate[winningPosition[1]]==gamestate[winningPosition[2]]&&
                      gamestate[winningPosition[0]]!=2)
              {
                  gameIsActive=false;
                  String winner="Zero";
                  if(gamestate[winningPosition[0]]==0)
                  {
                      winner="Cross";
                  }
                  TextView t1=(TextView)findViewById(R.id.t1);
                  t1.setText(winner +" "+ "has won!");
                  LinearLayout layout=(LinearLayout)findViewById(R.id.playagainlayout);
                  layout.setVisibility(View.VISIBLE);

              }
              else
              {
                  boolean gameIsOver=true;
                  for(int counterState:gamestate)
                  {
                      if(counterState==2)
                          gameIsOver=false;

                  }
                  if(gameIsOver)
                  {
                      TextView t1=(TextView)findViewById(R.id.t1);
                      t1.setText("It's a draw!");
                      LinearLayout layout=(LinearLayout)findViewById(R.id.playagainlayout);
                      layout.setVisibility(View.VISIBLE);

                  }
              }
            }
        }
    }

    public void PLAY(View view)
    {
        gameIsActive=true;
        LinearLayout layout=(LinearLayout)findViewById(R.id.playagainlayout);
        layout.setVisibility(View.INVISIBLE);
        activePlayer=1;
        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }
        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
