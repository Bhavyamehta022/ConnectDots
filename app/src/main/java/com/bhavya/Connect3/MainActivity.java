package com.bhavya.Connect3;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0=green 1=red
    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[] [] win={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameIsActive=true;
    LinearLayout layout;
    public void click(View view)
    {

        ImageView a=(ImageView) view ;

        System.out.println(a.getTag().toString());
        int tapped=Integer.parseInt(a.getTag().toString());
        if(gameState[tapped]==2&&gameIsActive) {
            gameState[tapped]=activePlayer;
            a.setTranslationY(-1000f);
            if (activePlayer == 0) {
                a.setImageResource(R.drawable.green_circle);
                activePlayer = 1;

            } else {
                a.setImageResource(R.drawable.red_circle);
                activePlayer = 0;

            }
            a.animate().translationYBy(1000f).rotation(360).setDuration(300);
        }


        for(int[] i: win)
        { String winner="Red";
            if(gameState[i[0]]==0)
                winner="Green";
            if((gameState[i[0]]==gameState[i[1]])&&(gameState[i[1]]==gameState[i[2]])&&(gameState[i[0]]!=2))
            {

                TextView b=(TextView)findViewById(R.id.editText2);
                b.setText(winner+" has won");
                LinearLayout layout=(LinearLayout)findViewById(R.id.playAgain);
                layout.setVisibility(View.VISIBLE);
                gameIsActive=false;

            }
            else
            {
                boolean gameOver = true;
                for(int c : gameState)
                {
                    if(c==2)
                        gameOver=false;
                }
                if(gameOver)
                {
                    TextView b=(TextView)findViewById(R.id.editText2);
                    b.setText("Draw");
                    LinearLayout layout=(LinearLayout)findViewById(R.id.playAgain);
                    layout.setVisibility(View.VISIBLE);
                }

            }
        }
        //b.animate().alpha(1f).setDuration(6000);
    }
    public void playAgain(View view)
    {
        activePlayer=0;
        gameIsActive=true;
        LinearLayout lay= (LinearLayout)findViewById(R.id.playAgain);
        lay.setVisibility(View.INVISIBLE);
     ConstraintLayout gr=findViewById(R.id.grid);
        for(int i=0;i<9;i++)
        {

            gameState[i]=2;
        }

        for(int i=0;i<gr.getChildCount();i++)
        {
           layout=(LinearLayout) gr.getChildAt(i);
           for(int j=0;j<layout.getChildCount();j++)
           {
               ((ImageView)layout.getChildAt(j)).setImageResource(0);
           }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
