package com.example.sr2_2020_android2021_projekat.adapters;

import static java.security.AccessController.getContext;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.Visibility;

import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.fragments.CreateEditCommunityFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Objects;

public class FlairCheckboxRecyclerAdapter extends
        RecyclerView.Adapter<FlairCheckboxRecyclerAdapter.ViewHolder> {

    private final List<String> flairsRecyclerView;
    private final CreateEditCommunityFragment createEditCommunityFragment;

    public FlairCheckboxRecyclerAdapter(CreateEditCommunityFragment createEditCommunityFragment, FragmentActivity activity, List<String> flairsRecyclerView) {
        this.flairsRecyclerView = flairsRecyclerView;
        this.createEditCommunityFragment = createEditCommunityFragment;
    }

    @NonNull
    @Override
    public FlairCheckboxRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                         int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_checkbox,
                parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String flair = this.flairsRecyclerView.get(position);

        holder.flairCheckBox.setText(flair);

        for(String flairName : createEditCommunityFragment.getSelectedCommunityFlairs())
            if(flairName.equals(flair))
                holder.flairCheckBox.setChecked(true);

        holder.flairCheckBox.setOnClickListener(v -> {
            if (((CheckBox) v).isChecked()) {
                String item = ((CheckBox) v).getText().toString();
                createEditCommunityFragment.getSelectedCommunityFlairs().add(item);
            }
            else {
                createEditCommunityFragment.getSelectedCommunityFlairs().remove(((CheckBox) v).getText().toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return flairsRecyclerView.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final CheckBox flairCheckBox;

        ViewHolder(View v) {
            super(v);

            flairCheckBox = v.findViewById(R.id.flair_checkbox);
        }

    }
}
