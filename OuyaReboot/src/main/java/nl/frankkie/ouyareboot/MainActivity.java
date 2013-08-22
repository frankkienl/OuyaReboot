package nl.frankkie.ouyareboot;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

import eu.chainfire.libsuperuser.Shell;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
    }


    public void initUI(){
        setContentView(R.layout.activity_main);
        findViewById(R.id.button_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RebootAsyncTask task = new RebootAsyncTask();
                task.execute("reboot");
            }
        });
        findViewById(R.id.button_recovery).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RebootAsyncTask task = new RebootAsyncTask();
                task.execute("reboot recovery");
            }
        });
        findViewById(R.id.button_turn_off).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RebootAsyncTask task = new RebootAsyncTask();
                task.execute("reboot -p");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public class RebootAsyncTask extends AsyncTask<String, Void, Void>{
        @Override
        protected Void doInBackground(String... params) {
            boolean suAvailable = Shell.SU.available();
            if (suAvailable) {
                Shell.SU.run(new String[] {
                        params[0]
                });
            }
            return null;
        }
    }
    
}
