package singlepageapp.mohanty.dinesh.com.booksearch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Book>> {


    EditText editText;
    ListView listView;
    ArrayList<Book> books;
    BookAdapter bookAdapter;
    ProgressBar progressBar;
    Button button;
    LoaderManager loaderManager;
    String keyword = "";

    String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.list_view);
        progressBar = (ProgressBar)findViewById(R.id.loading_indicator) ;
        progressBar.setVisibility(View.INVISIBLE);
        button = (Button)findViewById(R.id.search_button);
        editText = (EditText)findViewById(R.id.search) ;
        loaderManager = getSupportLoaderManager();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Book book = bookAdapter.getItem(position);
                Uri uri = Uri.parse(book.getLink());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                keyword = editText.getText().toString();
                loaderManager.restartLoader(1 , null , MainActivity.this);





            }
        });

        loaderManager.initLoader(1, null, MainActivity.this).forceLoad();

    }

    @Override
    public Loader<ArrayList<Book>> onCreateLoader(int id, Bundle args) {
        link = "https://www.googleapis.com/books/v1/volumes?q=" + keyword + "+intitle";

        return new BookLoader(MainActivity.this , link);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<Book>> loader, ArrayList<Book> data) {
        bookAdapter = new BookAdapter(MainActivity.this ,data);
        listView.setAdapter(bookAdapter);
        progressBar.setVisibility(View.INVISIBLE);
        if (keyword.equals("")) {
            listView.setVisibility(View.INVISIBLE);
        }
        else {
            listView.setVisibility(View.VISIBLE);
        }
    }



    @Override
    public void onLoaderReset(Loader<ArrayList<Book>> loader) {


        listView.setAdapter(null);



    }
}
