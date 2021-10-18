package com.moringaschool.nyaranga_social_app_version2.controller.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moringaschool.nyaranga_social_app_version2.R;
import com.moringaschool.nyaranga_social_app_version2.controller.NetworkUtils;
import com.moringaschool.nyaranga_social_app_version2.controller.Adapter.RepoListAdapter;
import com.moringaschool.nyaranga_social_app_version2.controller.models.Repository;

import java.io.IOException;
import java.util.List;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Repository>>{



    private ListView dataListView;
    private EditText requestTag;
    private TextView errorMessage;
    private ProgressBar loadingBar;
    private RepoListAdapter adapter;



    private static final int GITHUB_SEARCH_LOADER = 1;
    private static final String GITHUB_QUERY_TAG = "query";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        loadingBar = findViewById(R.id.loadingBar);
        errorMessage = findViewById(R.id.errorMessage);
        requestTag = findViewById(R.id.requestTag);
        //ListView And Set Empty View
        dataListView = findViewById(R.id.dataListView);
        dataListView.setEmptyView(errorMessage);

        //Repository Adapter
        adapter = new RepoListAdapter(getApplicationContext());
        dataListView.setAdapter(adapter);

        if (savedInstanceState != null) {
            String queryUrl = savedInstanceState.getString(GITHUB_QUERY_TAG);
            requestTag.setText(queryUrl);
        }

      //initializing loader
        getSupportLoaderManager().initLoader(GITHUB_SEARCH_LOADER, null, this);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int currentMenuId = item.getItemId();
        if (currentMenuId == R.id.searchMenu) {
            //Should Search Using AsyncTask Class
            //Call AsyncTask
            //new RepositoryThread().execute(query);
            makeGithubSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(GITHUB_QUERY_TAG, requestTag.getText().toString().trim());
    }

    @Override
    public Loader<List<Repository>> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<List<Repository>>(this) {
            List<Repository> mRepositoryList;

            @Override
            protected void onStartLoading() {

               //return null if no arguments
                if (args == null) {
                    return;
                }


                loadingBar.setVisibility(View.VISIBLE);


                if (mRepositoryList != null) {
                    deliverResult(mRepositoryList);
                } else {
                    forceLoad();
                }
            }

            @Override
            public List<Repository> loadInBackground() {

                String searchQueryUrlString = args.getString(GITHUB_QUERY_TAG);

                try {
                    List<Repository> githubSearchResults = NetworkUtils.getDataFromApi(searchQueryUrlString);
                    return githubSearchResults;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void deliverResult(List<Repository> githubJson) {
                mRepositoryList = githubJson;
                super.deliverResult(githubJson);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<List<Repository>> loader, List<Repository> data) {

        loadingBar.setVisibility(View.INVISIBLE);

        if (null == data) {
            showErrorMessage();
        } else {
            //Clear ListView Old Data
            adapter.clear();
            adapter.addAll(data);
            showJsonDataView();
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Repository>> loader) {

    }

    private void showJsonDataView() {
        /* First, make sure the error is invisible */
        errorMessage.setVisibility(View.INVISIBLE);
        /* Then, make sure the JSON data is visible */
        dataListView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        //hide vidible data
        dataListView.setVisibility(View.INVISIBLE);
       //now show error
        errorMessage.setVisibility(View.VISIBLE);
    }

    public void makeGithubSearchQuery() {
        String githubQuery = requestTag.getText().toString();


        Bundle queryBundle = new Bundle();
        queryBundle.putString(GITHUB_QUERY_TAG, githubQuery);
        LoaderManager loaderManager = getSupportLoaderManager();
        Loader<String> githubSearchLoader = loaderManager.getLoader(GITHUB_SEARCH_LOADER);
        if (githubSearchLoader == null) {
            loaderManager.initLoader(GITHUB_SEARCH_LOADER, queryBundle, this);
        } else {
            loaderManager.restartLoader(GITHUB_SEARCH_LOADER, queryBundle, this);
        }
    }


}