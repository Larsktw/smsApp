package zhet.smsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FloatingActionButtonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);

        Toolbar main_toolbar = (Toolbar) findViewById(R.id.fab_activity_toolbar);
        setSupportActionBar(main_toolbar);
        getSupportActionBar().setTitle(R.string.fab_tb_title);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ListView listView_fab = (ListView) findViewById(R.id.listView_fab);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Other_Messages));
        listView_fab.setAdapter(adapter);
    }
}
