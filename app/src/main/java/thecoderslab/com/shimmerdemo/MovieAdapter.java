package thecoderslab.com.shimmerdemo;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    ArrayList<MovieModal> MovieModalArrayList;


    public MovieAdapter(ArrayList<MovieModal> MovieModalArrayList) {
        this.MovieModalArrayList = MovieModalArrayList;

    }


    @NonNull
    @Override
    public MovieAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_single_row, parent, false);

        MovieAdapterViewHolder viewHolder = new MovieAdapterViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapterViewHolder holder, int position) {

        MovieModal item = MovieModalArrayList.get(position);

        ImageView movieThumbImageView = holder.movieThumbnailImageView;
        TextView titleTextView = holder.movieTitleTextView;
        TextView ratingTextView = holder.movieRatingTextView;
        TextView genreTextView = holder.genreTextView;

        String imageUrl = "https://image.tmdb.org/t/p/w185_and_h278_bestv2" + item.getPosterPath();

        Picasso.get().load(imageUrl).fit().centerCrop().into(movieThumbImageView);
        titleTextView.setText(item.getTitle());
        ratingTextView.setText(" " + item.getVoteAverage());
        genreTextView.setText(item.getGenre());

    }

    @Override
    public int getItemCount() {
        return (MovieModalArrayList == null) ? 0 : MovieModalArrayList.size();
    }

    public class MovieAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView movieTitleTextView, movieRatingTextView, genreTextView;
        ImageView movieThumbnailImageView;


        public MovieAdapterViewHolder(View itemView) {
            super(itemView);


            movieThumbnailImageView = itemView.findViewById(R.id.imageView_thumbnail);
            movieTitleTextView = itemView.findViewById(R.id.textView_movie_title);
            movieRatingTextView = itemView.findViewById(R.id.textView_tmdb_rating);
            genreTextView = itemView.findViewById(R.id.textView_movie_genre);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                //TODO
            }

        }
    }

}
