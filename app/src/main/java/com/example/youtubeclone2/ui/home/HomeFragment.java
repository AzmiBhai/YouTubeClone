package com.example.youtubeclone2.ui.home;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeclone2.R;
import com.example.youtubeclone2.ui.model.FiltersAdapter;
import com.example.youtubeclone2.ui.model.Profile;
import com.example.youtubeclone2.ui.model.ProfileAdapter;
import com.example.youtubeclone2.databinding.FragmentHomeBinding;
import com.example.youtubeclone2.ui.model.ProfileDataModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class HomeFragment extends Fragment {

    ArrayList<Profile> profiles;
    String[] filters = {"Explore","All","New to You","Live","Gaming"};
    RecyclerView recyclerView;
    ProfileAdapter adapter;

    private FragmentHomeBinding binding;
    private String search = "";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        profiles = new ArrayList<>();
        LinearLayout linearLayout = binding.linearLayout2;
        ImageView imageView = binding.image;


        Intent intent = getActivity().getIntent();
        if(intent.getStringExtra("searchterm") != null){
            search = intent.getStringExtra("searchterm");
        }
        profiles = ProfileDataModel.getProfiles();
        profiles = (ArrayList<Profile>) profiles.stream().filter(x -> x.getContentName().toLowerCase().contains(search.toLowerCase(Locale.ROOT))).collect(Collectors.toList());

        if (profiles.isEmpty()){
            Toast.makeText(getContext(), "No result found", Toast.LENGTH_SHORT).show();
            linearLayout.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);



        }else if(!search.equals("")){
            profiles = (ArrayList<Profile>) profiles.stream().filter(x -> x.getContentName().toLowerCase().contains(search.toLowerCase())).collect(Collectors.toList());
                    imageView.setVisibility(View.GONE);
        }

        linearLayout.setVisibility(View.VISIBLE);


        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),RecyclerView.VERTICAL,false));
        adapter  = new ProfileAdapter(getContext(),profiles);
        recyclerView.setAdapter(adapter);

        RecyclerView recyclerView2 = binding.recyclerView2;
        recyclerView2.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.HORIZONTAL, false));
        FiltersAdapter adapter2 = new FiltersAdapter(getContext(),filters);
        recyclerView2.setAdapter(adapter2);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}