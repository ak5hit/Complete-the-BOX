package tech.techbug.completethebox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class Game34Activity extends AppCompatActivity {

    int[][] rc = new int[9][4];
    int count = 0, flag1 = 0, flag2 = 0, flagbc = 0;
    int p1Boxs = 0, p2Boxs = 0;
    String p1Name, p2Name;
    TextView playerName, p1BoxCount, p2BoxCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game34);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        //Setting onClickListener on every line
        for(int i = 0; i < 9; ++i)
            if(i % 2 == 0)
                for(int j = 0; j < 3; ++j)
                    setClickListenerOnLine(i, j);
            else
                for(int j = 0; j < 4; ++j)
                    setClickListenerOnLine(i, j);


        //Linking Views in java from XML
        playerName = findViewById(R.id.playerName);
        p1BoxCount = findViewById(R.id.p1BoxCountTV);
        p2BoxCount = findViewById(R.id.p2BoxCountTV);

        //Receiving player names sent by start activity
        Intent intent = getIntent();
        p1Name = intent.getStringExtra("p1Name");
        p2Name = intent.getStringExtra("p2Name");

        //Initialising player name
        playerName.setText(p1Name);
    }


    /**
     * This method set onClickListener on line and handles players name and goes further if
     * game gets completed or not after every touch
     * @param row is row number of line
     * @param column is the column number of line
     */
    private void setClickListenerOnLine(final int row, final int column) {
        String transStrId = "l"+row+column;
        String lineStrId = "rl"+row+column;
        int lineViewId = getResources().getIdentifier(lineStrId, "id", getPackageName());
        int transLineViewId = getResources().getIdentifier(transStrId, "id", getPackageName());
        final View transLine = findViewById(transLineViewId);
        final View line = findViewById(lineViewId);
        transLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rc[row][column] == 1)
                    return;

                //Making the line black and checking if any box after this move gets completed or not
                line.setBackgroundColor(getResources().getColor(R.color.black));
                rc[row][column] = 1;
                isBoxCompletedThanDo(row, column);

                //Updating player name after every line touch
                if(count % 2 == 0)
                    playerName.setText(p1Name);
                else
                    playerName.setText(p2Name);

                //Checking is game gets completed than open other activity
                isGameCompletedThanDo();
            }
        });
    }

    /**
     * Check if any of the box gets completed related to the last line user touched
     * if it gets completed it fill that box with color respective to that user
     * @param row is row number of line
     * @param column is the column number of line
     */
    private void isBoxCompletedThanDo(int row, int column) {
        if(row % 2 == 1) {
            if(column-1 >= 0 && column+1 <= 3) {
                if(isLeftBoxCompleted(row, column)) {
                    fillLeftBox(row, column);
                    if (isRightBoxCompleted(row, column))
                        fillRightBox(row, column);
                    return;
                }
                if (isRightBoxCompleted(row, column)) {
                    fillRightBox(row, column);
                    if(isLeftBoxCompleted(row, column))
                        fillLeftBox(row, column);
                    return;
                }
            } else if(column == 0) {
                if(isRightBoxCompleted(row, column)) {
                    fillRightBox(row, column);
                    return;
                }
            } else if(column == 3)
                if(isLeftBoxCompleted(row, column)) {
                    fillLeftBox(row, column);
                    return;
                }
        } else if (row % 2 == 0) {
            if(row-2 >= 0 && row+2 <= 8) {
                if(isUpperBoxCompleted(row, column)) {
                    fillUpperBox(row, column);
                    if(isLowerBoxCompleted(row, column))
                        fillLowerBox(row, column);
                    return;
                }
                if(isLowerBoxCompleted(row, column)) {
                    fillLowerBox(row, column);
                    if(isUpperBoxCompleted(row, column))
                        fillUpperBox(row, column);
                    return;
                }
            } else if(row == 0) {
                if(isLowerBoxCompleted(row, column)) {
                    fillLowerBox(row, column);
                    return;
                }
            } else if(row == 8) {
                if(isUpperBoxCompleted(row, column)) {
                    fillUpperBox(row, column);
                    return;
                }
            }
        }
        flagbc = 0;
        if(flagbc == 0)
            count++;
    }

    /**
     * These methods check if a box gets completed after the last operation
     * @param row row number of the last line user touched
     * @param column column number of the last line user touched
     * @return true or false based on whether box gets completed or not
     */
    private boolean isLeftBoxCompleted(int row, int column) {
        return rc[row][column-1] == 1 && rc[row-1][column-1] == 1 && rc[row+1][column-1] == 1;
    }
    private boolean isRightBoxCompleted(int row, int column) {
        return rc[row][column+1] == 1 && rc[row-1][column] == 1 && rc[row+1][column] == 1;
    }
    private boolean isUpperBoxCompleted(int row, int column) {
        return rc[row-2][column] == 1 && rc[row-1][column] == 1 && rc[row-1][column+1] == 1;
    }
    private boolean isLowerBoxCompleted(int row, int column) {
        return rc[row+2][column] == 1 && rc[row+1][column] == 1 && rc[row+1][column+1] == 1;
    }


    /**
     * These methods fill the box based on turn of the user
     * @param row row number of the last line user touched
     * @param column column number of the last line user touched
     */
    private void fillLeftBox(int row, int column) {
        String strid = "b"+(row/2)+(column-1);
        int id = Game34Activity.this.getResources().getIdentifier(strid, "id",
                Game34Activity.this.getPackageName());
        View completedBox = findViewById(id);
        if(count % 2 == 1) {    flag2 = 1;  flagbc = 1; p2Boxs++;
            completedBox.setBackgroundColor(getResources().getColor(R.color.p2BoxColor));
            p2BoxCount.setText(Integer.toString(p2Boxs));
        }
        else {  flag1 = 1;  flagbc = 1; p1Boxs++;
            completedBox.setBackgroundColor(getResources().getColor(R.color.p1BoxColor));
            p1BoxCount.setText(Integer.toString(p1Boxs));
        }
    }
    private void fillRightBox(int row, int column) {
        String strid = "b"+(row/2)+column ;
        int id = Game34Activity.this.getResources().getIdentifier(strid, "id",
                Game34Activity.this.getPackageName());
        View completedBox = findViewById(id);
        if(count % 2 == 1) {    flag2 = 1;  flagbc = 1; p2Boxs++;
            completedBox.setBackgroundColor(getResources().getColor(R.color.p2BoxColor));
            p2BoxCount.setText(Integer.toString(p2Boxs));
        }
        else {  flag1 = 1;  flagbc = 1; p1Boxs++;
            completedBox.setBackgroundColor(getResources().getColor(R.color.p1BoxColor));
            p1BoxCount.setText(Integer.toString(p1Boxs));
        }
    }
    private void fillUpperBox(int row, int column) {
        String strid = "b"+((row/2)-1)+column;
        int id = Game34Activity.this.getResources().getIdentifier(strid, "id",
                Game34Activity.this.getPackageName());
        View completedBox = findViewById(id);
        if(count % 2 == 1) {    flag2 = 1;  flagbc = 1; p2Boxs++;
            completedBox.setBackgroundColor(getResources().getColor(R.color.p2BoxColor));
            p2BoxCount.setText(Integer.toString(p2Boxs));
        }
        else {  flag1 = 1;  flagbc = 1; p1Boxs++;
            completedBox.setBackgroundColor(getResources().getColor(R.color.p1BoxColor));
            p1BoxCount.setText(Integer.toString(p1Boxs));
        }
    }
    private void fillLowerBox(int row, int column) {
        String strid = "b"+(row/2)+column;
        int id = Game34Activity.this.getResources().getIdentifier(strid, "id",
                Game34Activity.this.getPackageName());
        View completedBox = findViewById(id);
        if(count % 2 == 1) {    flag2 = 1;  flagbc = 1; p2Boxs++;
            completedBox.setBackgroundColor(getResources().getColor(R.color.p2BoxColor));
            p2BoxCount.setText(Integer.toString(p2Boxs));
        }
        else {  flag1 = 1;  flagbc = 1; p1Boxs++;
            completedBox.setBackgroundColor(getResources().getColor(R.color.p1BoxColor));
            p1BoxCount.setText(Integer.toString(p1Boxs));
        }
    }

    /**
     *This method checks if all box gets filled and than send you to winner activity
     */
    private void isGameCompletedThanDo() {
        if(p1Boxs+p2Boxs == 12) {
            Intent winnerIntent = new Intent(Game34Activity.this, WinnerActivity.class);
            winnerIntent.putExtra("p1Name", p1Name);
            winnerIntent.putExtra("p2Name", p2Name);

            if(p1Boxs > p2Boxs) {
                winnerIntent.putExtra("winnerName", p1Name);
                winnerIntent.putExtra("p1Score", p1Boxs);
                winnerIntent.putExtra("p2Score", p2Boxs);
                winnerIntent.putExtra("draw", false);
            } else if(p1Boxs < p2Boxs) {
                winnerIntent.putExtra("winnerName", p2Name);
                winnerIntent.putExtra("p1Score", p1Boxs);
                winnerIntent.putExtra("p2Score", p2Boxs);
                winnerIntent.putExtra("draw", false);
            } else {
                winnerIntent.putExtra("winnerName", "DRAW");
                winnerIntent.putExtra("p1Score", p1Boxs);
                winnerIntent.putExtra("p2Score", p2Boxs);
                winnerIntent.putExtra("draw", true);
            }

            startActivity(winnerIntent);
            finish();
        }
    }
}