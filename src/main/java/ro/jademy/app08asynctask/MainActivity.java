package ro.jademy.app08asynctask;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    EditText et;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = et.getText().toString();
                int x = Integer.parseInt(s);

                MyCounter counter = new MyCounter();
                counter.execute(x);
                // new MyCounter().execute(x);
            }
        });
    }

    private class MyCounter extends AsyncTask<Integer, Integer, Void> {

        // inainte sa ruleze
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // in timp ce ruleaza pe alt thread
        @Override
        protected Void doInBackground(Integer... params) {
            for (int i = 0; i < params[0] ; i++) {
                publishProgress(i); // apeleaza onProgressUpdate
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        // dupa ce a terminat de rulat pe alt thread
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        // ruleaza pe ui thread la apelul metodei publishProgress()
        @Override
        protected void onProgressUpdate(Integer... values) {
            tv.setText(values[0] + "");
            super.onProgressUpdate(values);
        }


    }


}
