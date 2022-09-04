package com.example.sr2_2020_android2021_projekat.adapters;

import android.app.Dialog;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.sr2_2020_android2021_projekat.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SortByAdapter {

    public AutoCompleteTextView setSortByTypes(Context context, Dialog dialog, String sortBy) {

        AutoCompleteTextView autoCompleteTextView = dialog.findViewById(R.id.sort_by_types);

        List<String> sortByTypes = new ArrayList<>();

        sortByTypes.add("Hot");
        sortByTypes.add("New");
        sortByTypes.add("Top");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context,
                R.layout.drop_down_item, sortByTypes);

        autoCompleteTextView.setAdapter(adapter);

        if(sortBy != null && !sortBy.equals("")) {
            sortBy = sortBy.substring(0,1).toUpperCase(Locale.ROOT) + sortBy.substring(1);
            autoCompleteTextView.setText(sortBy, false);
        } else
            autoCompleteTextView.setText(autoCompleteTextView.getAdapter().
                    getItem(0).toString(), false);

        return autoCompleteTextView;
    }
}
