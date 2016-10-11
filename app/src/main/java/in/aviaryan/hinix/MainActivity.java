package in.aviaryan.hinix;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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

    private TableLayout tableLayout;
    private int NUM_ROWS=4;
    Map<Integer, String> myMap = new HashMap<Integer, String>();
    private  int NUM_COLS=4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<Integer> al=new ArrayList<Integer>();
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
                            if(row>=0 && column>=0 && row< NUM_ROWS && column<NUM_COLS)
                            {
                                TextView temp = (TextView) findViewById(fetchId(row,column));
                                Log.e("cbc",""+row+""+column+""+fetchId(row,column));

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

}