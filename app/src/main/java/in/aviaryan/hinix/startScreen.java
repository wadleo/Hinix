package in.aviaryan.hinix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static android.R.attr.value;

/**
 * Created by nilesh on 11/10/16.
 */

public class startScreen extends AppCompatActivity {

    private Button play, instruction;
    private RadioGroup rg;
    private RadioButton rb;
    private  int selectedId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        play=(Button)findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(startScreen.this, MainActivity.class);
                i.putExtra("Level",selectedId);
                startActivity(i);
            }
        });
        rg = (RadioGroup) findViewById(R.id.radiogrp);
        //final String value = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
         selectedId = rg.getCheckedRadioButtonId();
        //rb = (RadioButton) findViewById(selectedId);
       rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                selectedId=checkedId;
                Toast.makeText(getBaseContext(), selectedId+"", Toast.LENGTH_SHORT).show();
            }
        });
        instruction= (Button) findViewById(R.id.instr);
        instruction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        startScreen.this);

                // set title
                alertDialogBuilder.setTitle("Instructions");

                // set dialog message
                alertDialogBuilder
                        .setMessage("1. You are supposed to make as many words as you can from the give set of letters in the grid.\n" +
                                "There is a maximum no. of words possible. Your goal is to make that many words.\n" +
                                "2. There is a timer associated with each game. You are supposed to reach that maximum target in the given time.\n" +
                                "A fixed no. of points you get in each game. These points gets deducted based on the difference on the no. of possible words and your score.\n" +
                                "The game ends as soon as these points get over.\n" +
                                "Lastly you can challenge the game to show you all the possible words.\n\n");

                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });

    }

    public void level(){

    }
}
