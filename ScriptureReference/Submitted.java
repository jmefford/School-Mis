package cs246.jared.scripturereference;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Submitted extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submited);
        TextView submitted = (TextView) findViewById(R.id.submitedScripture);

        Bundle scripture = getIntent().getExtras();
        String books = scripture.getString("book");
        String chap = scripture.getString("chap");
        String vers = scripture.getString("vers");

        submitted.setText(books + " " + chap + ":" + vers);
    }
}
