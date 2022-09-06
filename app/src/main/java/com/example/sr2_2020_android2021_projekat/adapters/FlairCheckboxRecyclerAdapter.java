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
import com.example.sr2_2020_android2021_projekat.fragments.CreateEditPostFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Objects;

public class FlairCheckboxRecyclerAdapter extends
        RecyclerView.Adapter<FlairCheckboxRecyclerAdapter.ViewHolder> {

    private final List<String> flairsRecyclerView;
    private final CreateEditCommunityFragment createEditCommunityFragment;
    private final CreateEditPostFragment createEditPostFragment;

    public FlairCheckboxRecyclerAdapter(CreateEditCommunityFragment createEditCommunityFragment,
                                        CreateEditPostFragment createEditPostFragment,
                                        FragmentActivity activity,
                                        List<String> flairsRecyclerView) {

        this.flairsRecyclerView = flairsRecyclerView;
        this.createEditCommunityFragment = createEditCommunityFragment;
        this.createEditPostFragment = createEditPostFragment;
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

        if(createEditCommunityFragment != null) {
            for(String flairName : createEditCommunityFragment.getSelectedCommunityFlairs())
                if(flairName.equals(flair))
                    holder.flairCheckBox.setChecked(true);
        }

        if(createEditPostFragment != null) {

            for(String flairName : createEditPostFragment.getSelectedPostFlairs())
                if(flairName.equals(flair))
                    holder.flairCheckBox.setChecked(true);
        }

        holder.flairCheckBox.setOnClickListener(v -> {
            if (((CheckBox) v).isChecked()) {
                String item = ((CheckBox) v).getText().toString();

                if(createEditCommunityFragment != null) {
                    createEditCommunityFragment.getSelectedCommunityFlairs().add(item);
                }
                if(createEditPostFragment != null) {
                    createEditPostFragment.getSelectedPostFlairs().add(item);
                }

            }
            else {
                if(createEditCommunityFragment != null)
                    createEditCommunityFragment.getSelectedCommunityFlairs().remove(((CheckBox) v).getText().toString());
                if(createEditPostFragment != null)
                    createEditPostFragment.getSelectedPostFlairs().remove(((CheckBox) v).getText().toString());

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
