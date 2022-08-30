package com.example.youtubeclone2.ui.shorts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.youtubeclone2.databinding.FragmentShortsBinding;

public class ShortsFragment extends Fragment {

    private FragmentShortsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentShortsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textShorts;
        textView.setText("shorts");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}