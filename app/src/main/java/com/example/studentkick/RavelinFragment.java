package com.example.studentkick;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.studentkick.databinding.FragmentAboutBinding;
import com.example.studentkick.databinding.FragmentRavelinBinding;

public class RavelinFragment extends Fragment {

    FragmentRavelinBinding binding;

    @Nullable

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRavelinBinding.inflate(inflater);
        return binding.getRoot();


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;


    }

}
