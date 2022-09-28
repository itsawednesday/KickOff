package com.example.studentkick;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studentkick.databinding.FragmentSportsBinding;

public class SportsFragment extends Fragment {
    // private var _binding: FragmentSportsBinding? = null
    // private val binding: FragmentSportsBinding get() = _binding!!
    FragmentSportsBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSportsBinding.inflate(inflater);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        // _binding = null
    }
}
