package cs246.jared.multithreading;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.*;

public class MultiThread extends AppCompatActivity {
    ProgressBar progress;
    ArrayAdapter adapt;
    Thread backgroungThread;

    Write writer = new Write();
    Read reader = new Read();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_thread);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progress = (ProgressBar) findViewById(R.id.progressBar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multi_thread, menu);
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

    public void onPushCreate(View v) {
        backgroungThread = new Thread(writer);
        backgroungThread.start();
    }


    public void onPushLoad(View v) {
        backgroungThread = new Thread(reader);
        backgroungThread.start();
    }

    public void onPushClear(View v) {
        adapt.clear();
        progress.setProgress(0);
    }
    class Write implements Runnable {
        Context context;

        public void run() {
            File file = new File(context.getFilesDir(), "numbers.txt");
            String numbers;

            try
            {
                FileOutputStream output = openFileOutput("numbers.txt", Context.MODE_PRIVATE);
                for (int i = 1; i <= 10; i++) {
                    System.out.println(i);
                    numbers = i + "\n";
                    output.write(numbers.getBytes());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int temp = progress.getProgress();
                            progress.setProgress(temp + 1);
                        }
                    });
                    Thread.sleep(250);
                }
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class Read implements Runnable {
        List<Integer> list = new ArrayList<>();

        public void run() {
            int temp;
            String input;

            try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(openFileInput("numbers.txt")))) {
                while ((input = inputReader.readLine()) != null) {
                    temp = Integer.parseInt(input);
                    list.add(temp);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            int temp = progress.getProgress();
                            progress.setProgress(temp + 1);
                        }
                    });

                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException i) {
                        i.printStackTrace();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapt = new ArrayAdapter<>(MultiThread.this, android.R.layout.simple_list_item_1, list);
                    ListView view = (ListView) findViewById(R.id.listView);
                    view.setAdapter(adapt);
                }
            });
        }
    }
}

