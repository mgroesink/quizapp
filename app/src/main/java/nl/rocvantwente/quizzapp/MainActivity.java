package nl.rocvantwente.quizzapp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button b1 , b2 , b3 , b4 , b5 , b6 , b7 , b8 ;
    private RelativeLayout rl1 , rl2 , rl3 , rl4;
    private int[] scores = {0,0,0,0,0,0,0,0,};
    private int answered = 0;
    private View.OnClickListener obcl = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button b = (Button)view;
            int resourceId = getApplication().getResources().getIdentifier("rl" + b.getText(), "id", getApplication().getPackageName());
            RelativeLayout rl = (RelativeLayout)findViewById(resourceId);

            rl1.setVisibility(View.GONE);
            rl2.setVisibility(View.GONE);
            rl3.setVisibility(View.GONE);
            rl4.setVisibility(View.GONE);
            rl.setVisibility(View.VISIBLE);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        b5 = (Button)findViewById(R.id.button5);
        b6 = (Button)findViewById(R.id.button6);
        b7 = (Button)findViewById(R.id.button7);
        b8 = (Button)findViewById(R.id.button8);
        rl1 = (RelativeLayout)findViewById(R.id.rl1);
        rl2 = (RelativeLayout)findViewById(R.id.rl2);
        rl3 = (RelativeLayout)findViewById(R.id.rl3);
        rl4 = (RelativeLayout)findViewById(R.id.rl4);
        b1.setOnClickListener(obcl);
        b2.setOnClickListener(obcl);
        b3.setOnClickListener(obcl);
        b4.setOnClickListener(obcl);
        final CheckBox cbQ3_1 = (CheckBox)findViewById(R.id.checkBoxQ3_1);
        final CheckBox cbQ3_2 = (CheckBox)findViewById(R.id.checkBoxQ3_2);
        final CheckBox cbQ3_3 = (CheckBox)findViewById(R.id.checkBoxQ3_3);
        final CheckBox cbQ3_4 = (CheckBox)findViewById(R.id.checkBoxQ3_4);

        final Button bFinish = (Button)findViewById(R.id.buttonReset);
        final EditText edtAnswer1 = (EditText)findViewById(R.id.editTextQ1);


        bFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext() , getString(R.string.finalscore , calculateScore()) , Toast.LENGTH_LONG).show();
            }
        });
        Button bSubmit1 = (Button)findViewById(R.id.buttonSubmit1);
        bSubmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtAnswer1.getText().toString().replace(" " , "").toLowerCase().equals("structuredquerylanguage")){
                    scores[0] = 1;
                    Toast.makeText(getApplicationContext() , getText(R.string.success) , Toast.LENGTH_LONG).show();

                } else {
                    scores[0] = 0;
                    Toast.makeText(getApplicationContext() , getText(R.string.fail) , Toast.LENGTH_LONG).show();
                }
                b1.setEnabled(false);
                b1.setBackgroundColor(Color.rgb(54,54,54));
                view.setEnabled(false);
                edtAnswer1.setEnabled(false);
                answered++;
                if(answered == 2){
                    bFinish.setVisibility(View.VISIBLE);
                }
            }
        });

        Button bSubmit3 = (Button)findViewById(R.id.buttonSubmit3);
        bSubmit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbQ3_1.isChecked() && cbQ3_3.isChecked() && !cbQ3_2.isChecked() && ! cbQ3_4.isChecked()) {
                    scores[2] = 1;
                    Toast.makeText(getApplicationContext() , getText(R.string.success) , Toast.LENGTH_LONG).show();
                } else {
                    scores[2] = 0;
                    Toast.makeText(getApplicationContext() , getText(R.string.fail) , Toast.LENGTH_LONG).show();
                }
                b3.setEnabled(false);
                b3.setBackgroundColor(Color.rgb(54,54,54));
                view.setEnabled(false);
                cbQ3_1.setEnabled(false);
                cbQ3_2.setEnabled(false);
                cbQ3_3.setEnabled(false);
                cbQ3_4.setEnabled(false);
                answered++;
                if(answered == 2){
                    bFinish.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private int calculateScore() {
        int score = 0;
        for(int i : scores){
            score+=i;
        }
        return score;
    }
}
