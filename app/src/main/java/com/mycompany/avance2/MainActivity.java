package com.mycompany.avance2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ViewFlipper;

import java.io.OutputStreamWriter;


public class MainActivity extends Activity {
    private ViewFlipper viewFlipper;
    private float InitialX;
    private float FinalX;

    private String Hour;
    private String Minute;
    private String AmPm;
    private String Note;

    private EditText HOUR;
    private EditText MINUTE;
    private EditText AMPM;
    private EditText NOTE;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Refresh();
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
    }



    public boolean onTouchEvent(MotionEvent touchevent) {
        switch(touchevent.getAction() )
        {
            case MotionEvent.ACTION_DOWN:
                InitialX=touchevent.getX();
                break;

            case MotionEvent.ACTION_UP:
                FinalX=touchevent.getX();
                if(InitialX<(FinalX))
                {
                    viewFlipper.showPrevious();
                }
                if(InitialX>(FinalX))
                {
                    viewFlipper.showNext();
                }
                break;
            default:
        }

        return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void Save(View view)
    {
        HOUR=(EditText) findViewById(R.id.Hour);
        MINUTE=(EditText) findViewById(R.id.Minute);
        AMPM=(EditText) findViewById(R.id.AmPm);
        NOTE=(EditText) findViewById(R.id.Note);


        Hour=HOUR.getText().toString();
        Minute=MINUTE.getText().toString();
        AmPm=AMPM.getText().toString();
        Note=NOTE.getText().toString();


        String File_Name=Hour+Minute+AmPm+"txt";
        try {
            OutputStreamWriter fout= new OutputStreamWriter(openFileOutput(File_Name, Context.MODE_PRIVATE));
            fout.write(Note);
            fout.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
        }

        Refresh();
    }

    public void Refresh()
    {

    }
   }
