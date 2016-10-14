package zhet.smsapp;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {

    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ListView listView = (ListView)findViewById(R.id.list);
        arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, new ArrayList<String>());
        listView.setAdapter(arrayAdapter);

        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this,
                    MySuggestionProvider.AUTHORITY, MySuggestionProvider.MODE);
            suggestions.saveRecentQuery(query, null);
            doSearch(query);
        }
    }


    /**
     *
     * @param query
     */
    private void doSearch(String query) {
        query = query.toLowerCase();

        List<String> result = new ArrayList<>();
        for(String item : DomainSingleton.getSingleton(this).getData()) {
            if(item.toLowerCase().contains(query)) {
                result.add(item);
            }
        }

        doPresentResult(result);
    }


    /**
     *
     * @param results
     */
    private void doPresentResult(List<String> results) {
        arrayAdapter.addAll(results);
        arrayAdapter.notifyDataSetChanged();
    }
}
