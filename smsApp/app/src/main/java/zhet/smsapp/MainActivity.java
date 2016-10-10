package zhet.smsapp;

import android.Manifest;
import android.app.SearchManager;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView contactNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar main_toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(main_toolbar);
        getSupportActionBar().setTitle(R.string.main_tb_title);
        getSupportActionBar().setSubtitle(R.string.main_tb_subtitle);
        getSupportActionBar().setIcon(R.drawable.ic_toolbar);
        contactNames = (ListView) findViewById(R.id.contact_names);

        ListView listView_main = (ListView) findViewById(R.id.listView_main);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Messages));
        listView_main.setAdapter(adapter);

        FloatingActionButton fabActivity = (FloatingActionButton) findViewById(R.id.fab_main);
        fabActivity.setOnClickListener(new View.OnClickListener(){

             @Override
             public void onClick(View view) {
                  Intent intent = new Intent(MainActivity.this, FloatingActionButtonActivity.class);
                 startActivity(intent);
                }

            });

        FloatingActionButton fabActivity2 = (FloatingActionButton) findViewById(R.id.fab2_main);
        fabActivity2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ContentResolver contentResolver = getContentResolver();
                Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                        new String[] {ContactsContract.Contacts.DISPLAY_NAME}, null, null, null);

                List<String> contacts = new ArrayList<String>();
                if(cursor.moveToFirst()) {
                    do {
                        contacts.add(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
                    } while (cursor.moveToNext());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(), R.layout.contact_detail, R.id.name, contacts);
                contactNames.setAdapter(adapter);
            }
        });

        final int REQUEST_CODE_ASK_PERMISSIONS = 123;
        int hasReadContactsPermission =
                ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS);
        if(hasReadContactsPermission != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_CONTACTS)) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
        }

        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.READ_CONTACTS},
                REQUEST_CODE_ASK_PERMISSIONS);

        Button sendButtonPress = (Button) findViewById(R.id.sendButton);
        sendButtonPress.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Send melding", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_1:
                Toast.makeText(MainActivity.this, "Valg 1 presset", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_2:
                Toast.makeText(MainActivity.this, "Valg 2 presset", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_3:
                Toast.makeText(MainActivity.this, "Valg 3 presset", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
