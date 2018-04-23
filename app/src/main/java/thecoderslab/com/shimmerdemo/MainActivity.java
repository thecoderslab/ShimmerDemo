package thecoderslab.com.shimmerdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    String url = "https://api.themoviedb.org/3/movie/popular?api_key=XXXXXXXXXXXXX";
    MovieModal mMovieItems;
    ArrayList<MovieModal> mArrayList = new ArrayList<>();
    MovieAdapter mAdapter;
    RecyclerView mRecyclerView;
    ShimmerFrameLayout mShimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mShimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        mShimmerFrameLayout.startShimmer();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mArrayList.clear();
                mAdapter.notifyDataSetChanged();
                mShimmerFrameLayout.setVisibility(View.VISIBLE);

                //waits for 3 second before fetching data from url
                //did this so you can see the simmer effect
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fetchMovie(url);
                    }
                }, 3000);
            }
        });

        fetchMovie(url);
    }

    public void fetchMovie(String url) {

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray results = response.getJSONArray("results");

                            //looping through results array
                            for (int i = 0; i < results.length(); i++) {
                                int movieID = results.getJSONObject(i).getInt("id");
                                String title = results.getJSONObject(i).getString("title");
                                String posterPath = results.getJSONObject(i).getString("poster_path");
                                double vote_average = results.getJSONObject(i).getDouble("vote_average");

                                JSONArray genreArray = results.getJSONObject(i).getJSONArray("genre_ids");
                                String genre = "";
                                for (int j = 0; j < genreArray.length(); j++) {
                                    int id = genreArray.getInt(j);

                                    genre += getReadableGenre(id);
                                }
                                mMovieItems = new MovieModal(movieID, title, posterPath, genre, vote_average);

                                mArrayList.add(mMovieItems);
                                mAdapter = new MovieAdapter(mArrayList);
                                mRecyclerView.setAdapter(mAdapter);
                            }
                            // Stopping Shimmer Effect's animation after data is loaded to RecyclerView
                            mShimmerFrameLayout.stopShimmer();
                            mShimmerFrameLayout.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());

            }
        });
        VolleySingleton.getInstance().addToRequestQueue(request);
    }

    //convert the genre id to name
    public String getReadableGenre(int id) {
        StringBuilder genres = new StringBuilder();

        if (id == 28) {
            genres.append("action ");
        }
        if (id == 16) {
            genres.append("animated ");
        }
        if (id == 99) {
            genres.append("documentary ");
        }
        if (id == 18) {
            genres.append("dramas ");
        }
        if (id == 10751) {
            genres.append("family ");
        }
        if (id == 14) {
            genres.append("fantasy ");
        }
        if (id == 36) {
            genres.append("history ");
        }
        if (id == 35) {
            genres.append("comedy ");
        }
        if (id == 10752) {
            genres.append("war ");
        }
        if (id == 80) {
            genres.append("crime ");
        }
        if (id == 10402) {
            genres.append("music ");
        }
        if (id == 9648) {
            genres.append("mystery ");
        }
        if (id == 10749) {
            genres.append("romance ");
        }
        if (id == 878) {
            genres.append("sci-fi ");
        }
        if (id == 27) {
            genres.append("horror ");
        }
        if (id == 10770) {
            genres.append("Tv movies ");
        }
        if (id == 53) {
            genres.append("thriller ");
        }
        if (id == 37) {
            genres.append("western ");
        }
        if (id == 12) {
            genres.append("adventure ");
        }

        return genres.toString();

    }


}
