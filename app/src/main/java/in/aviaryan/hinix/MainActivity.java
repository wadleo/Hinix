package in.aviaryan.hinix;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    int presentId;
    String presentWord;

    Set<String> chodu = new HashSet<String>();




    private TableLayout tableLayout;
    private int NUM_ROWS=4;
    Map<Integer, String> myMap = new HashMap<Integer, String>();
    final ArrayList<Integer> al=new ArrayList<Integer>();
    private  int NUM_COLS=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tableLayout = (TableLayout) findViewById(R.id.grid);
        TextView tv = null;
        ViewGroup tvLayParams = null;

        for (int i = 0; i <NUM_ROWS; i++) {
            // Make TR
            TableRow tr = new TableRow(this);
            tr.setId(100 + i);
            tr.setLayoutParams(new TableRow.LayoutParams(GridLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            for (int j = 0; j < NUM_COLS; j++) {
                // Make TF to hold the details
                final TextView charTile = new TextView(this);
                charTile.setHeight(200);
                charTile.setTextSize(20);
                charTile.setWidth(200);
                charTile.setPadding(8,8,8,8);
                charTile.setBackgroundColor(Color.GREEN);
                charTile.setBackground(getDrawable(R.drawable.border));
                charTile.setId(NUM_ROWS * i + j);
                charTile.setText("A");

                charTile.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if(event.getAction() == MotionEvent.ACTION_UP){






                            // Do what you want
                            Calendar c = Calendar.getInstance();
                            int currentMinutes = c.get(Calendar.MINUTE);
                            int seconds = c.get(Calendar.SECOND);
                            myMap.put(charTile.getId(),""+currentMinutes +"");
                            int id= charTile.getId();
                            int row = id/NUM_ROWS;
                            int column = id%NUM_ROWS;

                            //setting present rows and present columns

                            presentId = fetchId(row,column);

                            if(al.size() == 0)
                            {
                                TextView backTemp1 = (TextView)findViewById(R.id.button);
                                backTemp1.setClickable(false);
                            }
                            if(al.size() != 0)
                            {
                                TextView backTemp1 = (TextView)findViewById(R.id.button);
                                backTemp1.setClickable(true);
                            }

                                    charTile.setBackgroundColor(Color.BLUE);
                            //disabling all tiles
                            for(int j = 0;j<NUM_ROWS*NUM_COLS;j++)
                            {
                                TextView temp = (TextView) findViewById(j);

                                temp.setClickable(false);
                            }


                            //get adjacent ids
                            if(row-1>=0 && column-1>=0 && row-1< NUM_ROWS && column-1<NUM_COLS)
                            {
                                TextView temp = (TextView) findViewById(fetchId(row-1,column-1));


                                temp.setClickable(true);
                            }
                            if(row+1>=0 && column+1>=0 && row+1< NUM_ROWS && column+1<NUM_COLS)
                            {
                                TextView temp = (TextView) findViewById(fetchId(row+1,column+1));

                                temp.setClickable(true);
                            }
                            if(row-1>=0 && column+1>=0 && row-1< NUM_ROWS && column+1<NUM_COLS)
                            {
                                TextView temp = (TextView) findViewById(fetchId(row-1,column+1));

                                temp.setClickable(true);
                            }
                            if(row-1>=0 && column>=0 && row-1< NUM_ROWS && column<NUM_COLS)
                            {
                                TextView temp = (TextView) findViewById(fetchId(row-1,column));

                                temp.setClickable(true);
                            }

                            if(row>=0 && column-1>=0 && row< NUM_ROWS && column-1<NUM_COLS)
                            {
                                TextView temp = (TextView) findViewById(fetchId(row,column-1));

                                temp.setClickable(true);
                            }
                            if(row>=0 && column+1>=0 && row< NUM_ROWS && column+1<NUM_COLS)
                            {
                                TextView temp = (TextView) findViewById(fetchId(row,column+1));

                                temp.setClickable(true);
                            }
                            if(row+1>=0 && column>=0 && row+1< NUM_ROWS && column<NUM_COLS)
                            {
                                TextView temp = (TextView) findViewById(fetchId(row+1,column));

                                temp.setClickable(true);
                            }
                            if(row+1>=0 && column-1>=0 && row+1< NUM_ROWS && column-1<NUM_COLS)
                            {
                                TextView temp = (TextView) findViewById(fetchId(row+1,column-1));

                                temp.setClickable(true);
                            }


                            //updating array list with ids of tiles
                            al.add(fetchId(row,column));
                            int alLength = al.size();
                            //Toast.makeText(getApplicationContext(), " row= "+row+ " Coloumn ="+column +"al - "+(alLength-1)+" "+al.get(alLength-1),
                                    //Toast.LENGTH_LONG).show();
                            String check = "";
                            for(int x=0;x<al.size();x++)
                            {
                                TextView temp = (TextView) findViewById(fetchId(row,column));
                                check = check+ temp.getText();
                            }
                            presentWord = check;
                            Toast.makeText(getApplicationContext(), " row= "+check,
                                    Toast.LENGTH_LONG).show();

                            return true;
                        }
                        return false;
                    }
                });
                charTile.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                        Calendar c = Calendar.getInstance();
                        int seconds = c.get(Calendar.SECOND);

                    }

                });
                tr.addView(charTile);

            }
            tableLayout.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        }
    }

    public int fetchId(int row, int col)
    {
        return row*NUM_ROWS + col;
    }

    public void clickBack(View view) {
        TextView backTemp = (TextView)findViewById(R.id.button);
        backTemp.setClickable(true);

        TextView temp2 = (TextView)findViewById(presentId);
        temp2.setBackgroundColor(Color.parseColor("#debebe"));

        int lenAL = al.size();
        al.remove(lenAL-1);


        if(lenAL - 2 < 0)
        {
            if(al.size() == 0)
            {
                TextView backTemp1 = (TextView)findViewById(R.id.button);
                backTemp1.setClickable(false);
            }
            if(al.size() != 0)
            {
                TextView backTemp1 = (TextView)findViewById(R.id.button);
                backTemp1.setClickable(true);
            }
        }
        else {

            int targetId = al.get(lenAL - 2);


            TextView temp1 = (TextView) findViewById(targetId);
            int row = targetId / NUM_ROWS;
            int column = targetId % NUM_ROWS;


            //disabling all tiles
            for (int j = 0; j < NUM_ROWS * NUM_COLS; j++) {
                TextView temp = (TextView) findViewById(j);

                temp.setClickable(false);
            }


            //get adjacent ids
            if (row - 1 >= 0 && column - 1 >= 0 && row - 1 < NUM_ROWS && column - 1 < NUM_COLS) {
                TextView temp = (TextView) findViewById(fetchId(row - 1, column - 1));


                temp.setClickable(true);
            }
            if (row + 1 >= 0 && column + 1 >= 0 && row + 1 < NUM_ROWS && column + 1 < NUM_COLS) {
                TextView temp = (TextView) findViewById(fetchId(row + 1, column + 1));

                temp.setClickable(true);
            }
            if (row - 1 >= 0 && column + 1 >= 0 && row - 1 < NUM_ROWS && column + 1 < NUM_COLS) {
                TextView temp = (TextView) findViewById(fetchId(row - 1, column + 1));

                temp.setClickable(true);
            }
            if (row - 1 >= 0 && column >= 0 && row - 1 < NUM_ROWS && column < NUM_COLS) {
                TextView temp = (TextView) findViewById(fetchId(row - 1, column));

                temp.setClickable(true);
            }

            if (row >= 0 && column - 1 >= 0 && row < NUM_ROWS && column - 1 < NUM_COLS) {
                TextView temp = (TextView) findViewById(fetchId(row, column - 1));

                temp.setClickable(true);
            }
            if (row >= 0 && column + 1 >= 0 && row < NUM_ROWS && column + 1 < NUM_COLS) {
                TextView temp = (TextView) findViewById(fetchId(row, column + 1));

                temp.setClickable(true);
            }
            if (row + 1 >= 0 && column >= 0 && row + 1 < NUM_ROWS && column < NUM_COLS) {
                TextView temp = (TextView) findViewById(fetchId(row + 1, column));

                temp.setClickable(true);
            }
            if (row + 1 >= 0 && column - 1 >= 0 && row + 1 < NUM_ROWS && column - 1 < NUM_COLS) {
                TextView temp = (TextView) findViewById(fetchId(row + 1, column - 1));

                temp.setClickable(true);
            }

            String check = "";
            for (int x = 0; x < al.size(); x++) {
                TextView temp = (TextView) findViewById(fetchId(row, column));
                check = check + temp.getText();
            }

            presentId = al.get(al.size() - 1);


            Toast.makeText(getApplicationContext(), " row= " + check,
                    Toast.LENGTH_LONG).show();

            if(al.size() == 0)
            {
                TextView backTemp1 = (TextView)findViewById(R.id.button);
                backTemp1.setClickable(false);
            }
            if(al.size() != 0)
            {
                TextView backTemp1 = (TextView)findViewById(R.id.button);
                backTemp1.setClickable(true);
            }

        }


    }

    public void buttonSubmit(View view) {

        //check in the library

        if(true)
        {
            chodu.add(presentWord);
            //handling addition of the new words
            String temp = presentWord;
            int lenUndo = presentWord.length();
            for(int k=0;k<lenUndo;k++)
            {
                undo();
            }

            int lenMap = chodu.size();
            String tempString = "";
            //appending the string of text view

            Toast.makeText(getApplicationContext(), " Wrong Word bro !!",
                    Toast.LENGTH_LONG).show();

        }
        else
        {
            Toast.makeText(getApplicationContext(), " Wrong Word bro !!",
                    Toast.LENGTH_LONG).show();
        }


    }

    public void undo()
    {
        TextView backTemp = (TextView)findViewById(R.id.button);
        backTemp.setClickable(true);

        TextView temp2 = (TextView)findViewById(presentId);
        temp2.setBackgroundColor(Color.parseColor("#debebe"));

        int lenAL = al.size();
        al.remove(lenAL-1);


        if(lenAL - 2 < 0)
        {
            if(al.size() == 0)
            {
                TextView backTemp1 = (TextView)findViewById(R.id.button);
                backTemp1.setClickable(false);
            }
            if(al.size() != 0)
            {
                TextView backTemp1 = (TextView)findViewById(R.id.button);
                backTemp1.setClickable(true);
            }
        }
        else {

            int targetId = al.get(lenAL - 2);


            TextView temp1 = (TextView) findViewById(targetId);
            int row = targetId / NUM_ROWS;
            int column = targetId % NUM_ROWS;


            //disabling all tiles
            for (int j = 0; j < NUM_ROWS * NUM_COLS; j++) {
                TextView temp = (TextView) findViewById(j);

                temp.setClickable(false);
            }


            //get adjacent ids
            if (row - 1 >= 0 && column - 1 >= 0 && row - 1 < NUM_ROWS && column - 1 < NUM_COLS) {
                TextView temp = (TextView) findViewById(fetchId(row - 1, column - 1));


                temp.setClickable(true);
            }
            if (row + 1 >= 0 && column + 1 >= 0 && row + 1 < NUM_ROWS && column + 1 < NUM_COLS) {
                TextView temp = (TextView) findViewById(fetchId(row + 1, column + 1));

                temp.setClickable(true);
            }
            if (row - 1 >= 0 && column + 1 >= 0 && row - 1 < NUM_ROWS && column + 1 < NUM_COLS) {
                TextView temp = (TextView) findViewById(fetchId(row - 1, column + 1));

                temp.setClickable(true);
            }
            if (row - 1 >= 0 && column >= 0 && row - 1 < NUM_ROWS && column < NUM_COLS) {
                TextView temp = (TextView) findViewById(fetchId(row - 1, column));

                temp.setClickable(true);
            }

            if (row >= 0 && column - 1 >= 0 && row < NUM_ROWS && column - 1 < NUM_COLS) {
                TextView temp = (TextView) findViewById(fetchId(row, column - 1));

                temp.setClickable(true);
            }
            if (row >= 0 && column + 1 >= 0 && row < NUM_ROWS && column + 1 < NUM_COLS) {
                TextView temp = (TextView) findViewById(fetchId(row, column + 1));

                temp.setClickable(true);
            }
            if (row + 1 >= 0 && column >= 0 && row + 1 < NUM_ROWS && column < NUM_COLS) {
                TextView temp = (TextView) findViewById(fetchId(row + 1, column));

                temp.setClickable(true);
            }
            if (row + 1 >= 0 && column - 1 >= 0 && row + 1 < NUM_ROWS && column - 1 < NUM_COLS) {
                TextView temp = (TextView) findViewById(fetchId(row + 1, column - 1));

                temp.setClickable(true);
            }

            String check = "";
            for (int x = 0; x < al.size(); x++) {
                TextView temp = (TextView) findViewById(fetchId(row, column));
                check = check + temp.getText();
            }

            presentId = al.get(al.size() - 1);


            Toast.makeText(getApplicationContext(), " row= " + check,
                    Toast.LENGTH_LONG).show();

            if(al.size() == 0)
            {
                TextView backTemp1 = (TextView)findViewById(R.id.button);
                backTemp1.setClickable(false);
            }
            if(al.size() != 0)
            {
                TextView backTemp1 = (TextView)findViewById(R.id.button);
                backTemp1.setClickable(true);
            }

        }
    }
}