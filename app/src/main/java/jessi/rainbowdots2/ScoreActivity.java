package jessi.rainbowdots2;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class ScoreActivity extends ListActivity {

    private DataBaseHandler db;
    ListView listView;
    Button tryAgainbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        tryAgainbutton=(Button)findViewById(R.id.tryAgainButton);
        tryAgainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        db = new DataBaseHandler(this);
        db.open();
        List<User> values = db.getAllUsers();

        if (values.isEmpty()) {
            db.defaultlist();
            values = db.getAllUsers();
        }

       final ArrayAdapter<User> adapter = new ArrayAdapter<User>(this, android.R.layout.simple_list_item_1,values);
        setListAdapter(adapter);
    }
}
