package sg.edu.rp.c346.dialogdemo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    Button btnDemo1;
    Button btnDemo2;
    Button btnDemo3;
    Button btnExercise3;
    Button btnDemo4;
    Button btnDemo5;

    TextView tvDemo1;
    TextView tvDemo2;
    TextView tvDemo3;
    TextView tvExercise3;
    TextView tvDemo4;
    TextView tvDemo5;

// for current date
   int theHour, theMin, theYear,theMonth,theDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDemo1 = findViewById(R.id.buttonDemo1);
        btnDemo2 = findViewById(R.id.buttonDemo2);
        btnDemo3 = findViewById(R.id.buttonDemo3);
        btnExercise3 = findViewById(R.id.buttonExercise3);
        btnDemo4 = findViewById(R.id.buttonDemo4);
        btnDemo5 = findViewById(R.id.buttonDemo5);

        tvDemo1 = findViewById(R.id.textView1);
        tvDemo2 = findViewById(R.id.textView2);
        tvDemo3 = findViewById(R.id.textView3);
        tvExercise3= findViewById(R.id.textViewExercise3);
        tvDemo4 = findViewById(R.id.textView4);
        tvDemo5 = findViewById(R.id.textView5);

        //Get Current date and time 55 - 60
        Calendar now = Calendar.getInstance();
        theYear = now.get(Calendar.YEAR);
        theDay = now.get(Calendar.DAY_OF_MONTH);
        theMonth = now.get(Calendar.MONTH);
        theMin = now.get(Calendar.MINUTE);
        theHour = now.get(Calendar.HOUR);


        btnDemo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                myBuilder.setTitle("Congratulations");
                myBuilder.setMessage("You have completed a simple dialog box");
                myBuilder.setCancelable(false);
                myBuilder.setPositiveButton("Close",null);

                // Actual Dialog
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnDemo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                myBuilder.setTitle("Demo 2 - Buttons Dialog");
                myBuilder.setMessage("Select one of the Button below");
                myBuilder.setCancelable(false);


                myBuilder.setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        tvDemo2.setText("You have selected Positive");

                    }
                });
                myBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        tvDemo2.setText("You have selected negative/positive");
                    }
                });

                myBuilder.setNeutralButton("Cancel",null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });




        btnDemo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// line 89-92 make custom layout - handle input.xml file which is to be shown on the dialog
                LayoutInflater inflater = (LayoutInflater)
                        getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View viewDialog = inflater.inflate(R.layout.input, null);

                final EditText etInput = viewDialog.findViewById(R.id.editTextInput);
//                String message = etInput.getText().toString();

                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);

                myBuilder.setView(viewDialog);
                myBuilder.setCancelable(false);
                myBuilder.setTitle("Demo 3 - Text Input Dialog");
                myBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Extract the text entered by user
                        String message = etInput.getText().toString();
                        tvDemo3.setText(message);
                    }
                });

                // Actual Dialog
                myBuilder.setNegativeButton("CANCEL",null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnExercise3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // line 89-92 make custom layout - handle input.xml file which is to be shown on the dialog
                LayoutInflater inflater = (LayoutInflater)
                        getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                View viewDialog = inflater.inflate(R.layout.input2, null);

                final EditText etInput1 = viewDialog.findViewById(R.id.editNum1);
                final EditText etInput2 = viewDialog.findViewById(R.id.editNum2);
// Builder
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);
                // show Title
                myBuilder.setTitle("Exercise 3");
                //show the custome XML layout file
                myBuilder.setCancelable(false);
                // set the Dialog view - see line 132
                myBuilder.setView(viewDialog);

                myBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String num1 = etInput1.getText().toString();
                        String num2 = etInput2.getText().toString();
                        int sum = Integer.parseInt(num1) + Integer.parseInt(num2);
                        tvExercise3.setText("The sum is: " + sum);
                    }
                });
                // Actual Dialog
                myBuilder.setNegativeButton("CANCEL",null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnDemo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create the Listener to set date
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvDemo4.setText("Date: " + dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
                        theYear = year;
                        theMonth = monthOfYear;
                        theDay = dayOfMonth;
                    }
                };
                //Create the Date Picker Dialog
                // if theYear theMonth theDay not initialize, use the fields in onDateSet() line 186
                DatePickerDialog myDateDialog = new DatePickerDialog(MainActivity.this,
                        myDateListener,theYear,theMonth,theDay);
                myDateDialog.show();
            }
        });

        btnDemo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Create the Listener to set date
                TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tvDemo5.setText("Time: " + hourOfDay + ":" + minute);
                        theHour = hourOfDay;
                        theMin = minute;

                    }
                };
                //Create the Date Picker Dialog
                // if theYear theMonth theDay not initialize, use the fields in onDateSet() line 186
                TimePickerDialog myTimeDialog = new TimePickerDialog(MainActivity.this,
                        myTimeListener,theHour,theMin,true);
                myTimeDialog.show();
            }
        });
    }
}



