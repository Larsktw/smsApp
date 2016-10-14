package zhet.smsapp;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);

        Toolbar main_toolbar = (Toolbar) findViewById(R.id.search_results_toolbar);
        setSupportActionBar(main_toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String query = new String();
        Intent seatchIntent = getIntent();
        if(Intent.ACTION_SEARCH.equals(seatchIntent.getAction())) {
            query = seatchIntent.getStringExtra(SearchManager.QUERY);
            getSupportActionBar().setTitle(query);
            Toast.makeText(SearchResultsActivity.this, query, Toast.LENGTH_SHORT).show();
        }

        String[] messages = getResources().getStringArray(R.array.Messages);
        ArrayList <String> searchResults = new ArrayList<String>();
        for (int i=0; i<messages.length; i++)
            if (messages[i].toLowerCase().contains(query.toLowerCase()))
                searchResults.add(messages[i]);

        ListView listView_main = (ListView) findViewById(R.id.listView_searchResults);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, searchResults);
        listView_main.setAdapter(adapter);
    }
}
