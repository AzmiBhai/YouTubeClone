package com.example.youtubeclone2.ui.subscription;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.youtubeclone2.databinding.FragmentSubscriptionBinding;
import com.example.youtubeclone2.databinding.FragmentSubscriptionBinding;

public class SubscriptionFragment extends Fragment {

    private FragmentSubscriptionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentSubscriptionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textSubscription;
        textView.setText("Subscription");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}