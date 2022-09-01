package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.api.RetrofitRepository;
import com.example.sr2_2020_android2021_projekat.model.RegisterRequest;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.example.sr2_2020_android2021_projekat.tools.HttpClient;

import java.util.Objects;

public class RegisterFragment extends Fragment {

    private com.google.android.material.textfield.TextInputEditText username;
    private com.google.android.material.textfield.TextInputEditText displayName;
    private com.google.android.material.textfield.TextInputEditText email;
    private com.google.android.material.textfield.TextInputEditText password;
    private com.google.android.material.textfield.TextInputEditText passwordRepeated;
    private com.google.android.material.textfield.TextInputEditText description;

    private final HttpClient httpClient = new HttpClient();

    public static RegisterFragment newInstance() {

        return new RegisterFragment();

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup vg, Bundle data) {

        ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                false);

        getActivity().setTitle("Register to Reddit Clone");

        ((MainActivity)getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_user_register).setChecked(true);

        View view = inflater.inflate(R.layout.fragment_register, vg, false);

        username = view.findViewById(R.id.username);
        displayName = view.findViewById(R.id.displayName);
        email = view.findViewById(R.id.email);
        password = view.findViewById(R.id.password);
        passwordRepeated = view.findViewById(R.id.passwordRepeated);
        description = view.findViewById(R.id.description);

        Button registerButton = view.findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isUsernameValid = username.getText().toString().length() > 0;
                boolean isDisplayNameValid = displayName.getText().toString().length() > 0;
                boolean isEmailValid = email.getText().toString().length() > 0 &&
                        email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
                boolean isPasswordValid = password.getText().toString().length() > 0;
                boolean isRepeatedPasswordValid = password.getText().toString().length() > 0 &&
                        passwordRepeated.getText().toString().equals(password.getText().toString());
                boolean isDescriptionValid = description.getText().toString().length() > 0;


                if(!isUsernameValid) {

                    Toast.makeText(view.getContext(), "Please provide a valid username",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                if(!isDisplayNameValid) {

                    Toast.makeText(view.getContext(), "Please provide a valid display name",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                if(!isEmailValid) {

                    Toast.makeText(view.getContext(), "Please provide a valid email",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                if(!isPasswordValid) {

                    Toast.makeText(view.getContext(), "Please provide a valid password",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                if(!isRepeatedPasswordValid) {

                    Toast.makeText(view.getContext(), "Passwords are not matching",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                if(!isDescriptionValid) {

                    Toast.makeText(view.getContext(), "Please provide a valid description",
                            Toast.LENGTH_SHORT).show();

                    return;
                }

                register(view);

            }
        });

        return view;
    }

    private void register(View view) {

        RetrofitRepository<String> retrofitRepository = new RetrofitRepository<>();

        RegisterRequest registerRequest = new RegisterRequest(
                Objects.requireNonNull(username.getText()).toString(),
                Objects.requireNonNull(password.getText()).toString(),
                Objects.requireNonNull(email.getText()).toString(), "",
                Objects.requireNonNull(description.getText()).toString(),
                Objects.requireNonNull(displayName.getText()).toString(), false);

        retrofitRepository.sendRequest(httpClient.routes.register(registerRequest), view, () ->
                Toast.makeText(getContext(), "User registration is successful",
                Toast.LENGTH_LONG).show());

        if(getActivity() != null)
            FragmentTransition.to(PostsFragment.newInstance(), getActivity(),
                    false, R.id.viewPage);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //Toast.makeText(getActivity(), "onActivityCreated()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //Toast.makeText(getActivity(), "onAttach()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //Toast.makeText(getActivity(), "onDestroyView()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //Toast.makeText(getActivity(), "onDeatach()", Toast.LENGTH_SHORT).show();
    }

}
