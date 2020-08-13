package in.arjunsah.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;
    //player representation
    //0 - x
    //1 - o

    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    //    gameState meaning
    //    0 - X
    //    1 - O
    //    2 - null
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                            {0,4,8}, {2,4,6}};

    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive){
            gameReset(view);
        }
        if(gameState[tappedImage] == 2){
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if(activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's Turn - Tap to Play");
            }
            else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's Turn - Tap to Play");
            }
        img.animate().translationYBy(1000f).setDuration(300);
        }

        //check for winner
        for(int[] winPosition:winPositions){
            if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                gameState[winPosition[1]] == gameState[winPosition[2]] &&
                gameState[winPosition[0]]!=2){
                //someBody has won -find out Who
                gameActive = false;
                String winnerStr;
                if(gameState[winPosition[0]] == 0){
                    winnerStr = "X has Won!";
                }
                else {
                    winnerStr = "O has Won!";
                }
                // updating status for winner announcement
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
            }
        }
    }

    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }
        ((ImageView)findViewById(R.id.cell_1)).setImageResource(0);
        ((ImageView)findViewById(R.id.cell_2)).setImageResource(0);
        ((ImageView)findViewById(R.id.cell_3)).setImageResource(0);
        ((ImageView)findViewById(R.id.cell_4)).setImageResource(0);
        ((ImageView)findViewById(R.id.cell_5)).setImageResource(0);
        ((ImageView)findViewById(R.id.cell_6)).setImageResource(0);
        ((ImageView)findViewById(R.id.cell_7)).setImageResource(0);
        ((ImageView)findViewById(R.id.cell_8)).setImageResource(0);
        ((ImageView)findViewById(R.id.cell_9)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to Play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
