package com.example.sr2_2020_android2021_projekat.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sr2_2020_android2021_projekat.R;

import java.util.List;

public class FlairButtonRecyclerAdapter extends RecyclerView.Adapter<FlairButtonRecyclerAdapter.ViewHolder> {

    private final List<String> flairsRecyclerView;

    public FlairButtonRecyclerAdapter(FragmentActivity fragmentActivity, List<String> flairsRecyclerView) {
        this.flairsRecyclerView = flairsRecyclerView;
    }

    @NonNull
    @Override
    public FlairButtonRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_flair,
                parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FlairButtonRecyclerAdapter.ViewHolder holder, int position) {

        String flair = this.flairsRecyclerView.get(position);

        holder.flairButtonText.setText(flair);

    }

    @Override
    public int getItemCount() {

        return flairsRecyclerView.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView flairButtonText;

        ViewHolder(View v) {
            super(v);

            flairButtonText = v.findViewById(R.id.flair_button);

        }
    }

}
