package com.android.MusicStructureApp;


import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.MusicStructureApp.databinding.AlbumGridItemBinding;
import com.android.MusicStructureApp.databinding.FragmentSongBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class AlbumFragment extends Fragment {
    private ArrayList<Song> albumList = new ArrayList<>();


    public AlbumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSongBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_song, container, false);

        getSongs();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        AlbumAdapter adapter = new AlbumAdapter();

        binding.songRecyclerView.setLayoutManager(gridLayoutManager);
        binding.songRecyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

    //Adapter class for RecyclerView
    private class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumHolder> {

        @Override
        public AlbumAdapter.AlbumHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            AlbumGridItemBinding itemBinding = AlbumGridItemBinding.inflate(layoutInflater, parent, false);
            return new AlbumHolder(itemBinding);
        }

        @Override
        public void onBindViewHolder(AlbumHolder holder, int position) {
            Song song = albumList.get(position);
            holder.bind(song);
        }

        @Override
        public int getItemCount() {
            return albumList.size();
        }

        public class AlbumHolder extends RecyclerView.ViewHolder {
            private AlbumGridItemBinding binding;

            public AlbumHolder(AlbumGridItemBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            public void bind(Song song) {
                binding.setSong(song);
                binding.executePendingBindings();
                binding.albumImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), PlayingActivity.class);
                        intent.putExtra("LIST", albumList);
                        intent.putExtra("POSITION", getAdapterPosition());
                        startActivity(intent);
                    }
                });
            }
        }
    }


    @BindingAdapter("android:src")
    public static void setImageResource(ImageView imageView, int resource){
        imageView.setImageResource(resource);
    }

    private void getSongs() {
        Song[] songs = new Song[14];
        songs[0] = new Song(1, "Alphabhet the spell", "Nick", "Alphabhet", R.drawable.alpha);
        songs[1] = new Song(2, "True", "Avicii", "True", R.drawable.avicii);
        songs[2] = new Song(3, "Attention", "Charlie puth", "Attention", R.drawable.charlie);
        songs[3] = new Song(4, "In the End", "Linkin Park", "Hybrid Theory", R.drawable.linkinpark);
        songs[4] = new Song(5, "Little Mix", "Jason Derulo", "Little Mix", R.drawable.little);
        songs[5] = new Song(6, "Get Weird", "Jason Derulo", "Little Mix", R.drawable.littlemix);
        songs[6] = new Song(7, "Home", "Zara ", "One Direction", R.drawable.one);
        songs[7] = new Song(8, "La Confidential", "Tory Lanez", "La Confidential", R.drawable.tory);
        songs[8] = new Song(9, "Tuesday", "Drake", "Tuesday", R.drawable.tuesday);
        songs[9] = new Song(10, "PillowTalk", "Zayn", "Zayn", R.drawable.zayn);
        songs[10] = new Song(11, "Levels", "Nick Jones", "Levels", R.drawable.nickjones);
        songs[11] = new Song(12, "Unleashed", "Skillet", "Unleashed", R.drawable.skillet);
        songs[12] = new Song(13, "Symphony", "Suicide Squad", "Suicide Squad", R.drawable.sucidesquad);
        songs[13] = new Song(14, "Youth", "Troye Sivan", "Youth", R.drawable.youth);

        albumList.addAll(Arrays.asList(songs));
    }

}
