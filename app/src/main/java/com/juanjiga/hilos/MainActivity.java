package com.juanjiga.hilos;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button, button2, button3;
    TextView textView;
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        textView = (TextView) findViewById((R.id.textView));
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);


        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }
    void UnSegundo(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void Hilos(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 5; i++) {
                    UnSegundo();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getBaseContext(), "Tarea 5 sg finalizada", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                Toast.makeText(getBaseContext(), "Empieza Tarea 10 sg.", Toast.LENGTH_SHORT).show();
            for (int i=0; i<=10; i++){
                UnSegundo();
            }
                Toast.makeText(getBaseContext(), "Tarea 10 sg. finalizada", Toast.LENGTH_SHORT).show();
            break;
            case R.id.button2:
                Hilos();
            break;
            case R.id.button3:
                new tarea().execute();
            break;
        default:
            break;
        }
    }

    private class tarea extends AsyncTask<Void, Integer, Boolean>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setMax(100);
            mProgressBar.setProgress(0);
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            for (int i=0; i<=10; i++) {
                UnSegundo();
                publishProgress(i*10);
            }
            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mProgressBar.setProgress(values[0].intValue());
            textView.setText("segundo " + (values[0].intValue()) / 10);
        }

        @Override
        protected void onPostExecute(Boolean resultado) {
            super.onPostExecute(resultado);
            if (resultado){
                Toast.makeText(getBaseContext(),"Tarea finalizada en AsyncTask", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Toast.makeText(getBaseContext(),"Tarea cancelada", Toast.LENGTH_SHORT).show();
        }


    }
}
