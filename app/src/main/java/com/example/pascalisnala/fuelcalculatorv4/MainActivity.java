package com.example.pascalisnala.fuelcalculatorv4;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.Math;

public class MainActivity extends AppCompatActivity {
    private EditText BlockFuel;
    private EditText FuelRemaining;
    private EditText Gravity;
    private TextView Planned;
    private TextView PlannedVol;

    private TextView Right;
    private TextView Left;
    private TextView Center;

    private TextView Title1;
    private TextView Title2;
    private TextView Title3;

    private TextView Copyright;
    private TextView Author;

    private Button Reset;

    private int plan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BlockFuel = findViewById(R.id.etBlockFuel);
        BlockFuel.addTextChangedListener(watchPlannedUplift);

        FuelRemaining = findViewById(R.id.etRemaining);
        FuelRemaining.addTextChangedListener(watchPlannedUplift);

        Gravity = findViewById(R.id.etGravity);
        Gravity.addTextChangedListener(watchGravity);

        Planned = findViewById(R.id.tvPlanned);
        Planned.addTextChangedListener(watchGravity);

        PlannedVol = findViewById(R.id.tvPlannedVol);
        PlannedVol.addTextChangedListener(watchAircraft);

        Right = findViewById(R.id.tvRight);
        Center = findViewById(R.id.tvCenter);
        Left = findViewById(R.id.tvLeft);

        Reset = findViewById(R.id.btnReset);
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        Title1 = findViewById(R.id.tvBravo);
        Title2 = findViewById(R.id.tvFuelCalculator);
        Title3 = findViewById(R.id.tvBoeing);

        Copyright = findViewById(R.id.copyright);
        Author = findViewById(R.id.author);

        Typeface Montserrat = Typeface.createFromAsset(getAssets(),"fonts/Montserrat-Regular.ttf");

        Title1.setTypeface(Montserrat);
        Title2.setTypeface(Montserrat);
        Title3.setTypeface(Montserrat);

        Copyright.setTypeface(Montserrat);
        Author.setTypeface(Montserrat);



    }

    private TextWatcher watchPlannedUplift = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            int intblockfuel;
            int intfuelremaining;
            String blockfuel, fuelremaining;
            blockfuel = BlockFuel.getText().toString();
            fuelremaining = FuelRemaining.getText().toString();
            if(!((TextUtils.isEmpty(blockfuel))||(TextUtils.isEmpty(fuelremaining)))) {
                intblockfuel = Integer.parseInt(BlockFuel.getText().toString());
                intfuelremaining = Integer.parseInt(FuelRemaining.getText().toString());
                planneduplift(intblockfuel, intfuelremaining);
            }else{
                Planned.setText("0");
            }

        }
    };

    private TextWatcher watchGravity = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            int intgravity;
            int intlatestplan;
            String gravity;
            int gravitylength;
            double divider;
            String latestplan;

            latestplan = Planned.getText().toString();
            intlatestplan = Integer.parseInt(latestplan);
            gravity = Gravity.getText().toString();

            if (!(TextUtils.isEmpty(gravity))){
                gravitylength = Gravity.getText().length();
                divider =  Math.pow(10,gravitylength);
                intgravity = Integer.parseInt(gravity);
                if (intgravity > 0) {
                    plannedvolume(intgravity, intlatestplan, divider);
                }else{
                    PlannedVol.setText("0");
                }
            }else{
                PlannedVol.setText("0");
            }
        }
    };

    private TextWatcher watchAircraft = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            int kiri,kanan,tengah;

            int intblockfuel;


            if(!(TextUtils.isEmpty(BlockFuel.getText().toString()))){
                intblockfuel = Integer.parseInt(BlockFuel.getText().toString());
                if (intblockfuel >7700){
                    kiri = 3850;
                    kanan = 3850;
                    tengah = intblockfuel-7700;

                }else{
                    kiri = intblockfuel/2;
                    kanan = kiri;
                    tengah = 0;
                }


            }else{
                kiri = 0;
                kanan = 0;
                tengah = 0;
            }

            Left.setText(String.valueOf(kiri));
            Right.setText(String.valueOf(kanan));
            Center.setText(String.valueOf(tengah));



        }
    };

    private void planneduplift (int BlockFuel, int FuelRemaining){
        plan = BlockFuel - FuelRemaining;
        Planned.setText(String.valueOf(plan));
    }

    private void plannedvolume (int gravity, int planneduplift, double divider){
        double planvol;
        planvol = (planneduplift/(gravity/divider));
        PlannedVol.setText(String.format("%.1f",planvol));
    }

    private void reset (){
        BlockFuel.setText("");
        FuelRemaining.setText("");
        Planned.setText("0");
        Gravity.setText("");
        PlannedVol.setText("0");
        Left.setText("0");
        Right.setText("0");
        Center.setText("0");
    }
}
