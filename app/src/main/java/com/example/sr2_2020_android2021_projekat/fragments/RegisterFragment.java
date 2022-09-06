package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.api.RetrofitRepository;
import com.example.sr2_2020_android2021_projekat.model.AuthResponse;
import com.example.sr2_2020_android2021_projekat.model.FileResponse;
import com.example.sr2_2020_android2021_projekat.model.LoginRequest;
import com.example.sr2_2020_android2021_projekat.model.RegisterRequest;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.example.sr2_2020_android2021_projekat.tools.HttpClient;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MultipartBody;

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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup vg, Bundle data) {

        httpClient.setContext(getContext());

        if(getActivity() != null)
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
        com.google.android.material.button.MaterialButton uploadAvatarButton =
                view.findViewById(R.id.button_avatar_upload);

        Button registerButton = view.findViewById(R.id.registerButton);

        registerButton.setOnClickListener(v -> {

            if (validateData(view)) return;

            register(view);

        });

        uploadAvatarButton.setOnClickListener(v ->
                ((MainActivity)getActivity()).openFileChooser());

        return view;
    }

    private boolean validateData(View view) {

        boolean isUsernameValid = Objects.requireNonNull(username.getText()).
                toString().length() > 0;
        boolean isDisplayNameValid = Objects.requireNonNull(displayName.getText()).
                toString().length() > 0;
        boolean isEmailValid = Objects.requireNonNull(email.getText()).
                toString().length() > 0 && email.getText().toString().
                matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");
        boolean isPasswordValid = Objects.requireNonNull(password.getText()).
                toString().length() > 0;
        boolean isRepeatedPasswordValid = password.getText().toString().length() > 0 &&
                Objects.requireNonNull(passwordRepeated.getText()).toString().equals(
                        password.getText().toString());
        boolean isDescriptionValid = Objects.requireNonNull(description.getText()).
                toString().length() > 0;

        if(!isUsernameValid) {

            Toast.makeText(view.getContext(), "Please provide a valid username",
                    Toast.LENGTH_SHORT).show();

            return true;
        }

        if(!isDisplayNameValid) {

            Toast.makeText(view.getContext(), "Please provide a valid display name",
                    Toast.LENGTH_SHORT).show();

            return true;
        }

        if(!isEmailValid) {

            Toast.makeText(view.getContext(), "Please provide a valid email",
                    Toast.LENGTH_SHORT).show();

            return true;
        }

        if(!isPasswordValid) {

            Toast.makeText(view.getContext(), "Please provide a valid password",
                    Toast.LENGTH_SHORT).show();

            return true;
        }

        if(!isRepeatedPasswordValid) {

            Toast.makeText(view.getContext(), "Passwords are not matching",
                    Toast.LENGTH_SHORT).show();

            return true;
        }

        if(!isDescriptionValid) {

            Toast.makeText(view.getContext(), "Please provide a valid description",
                    Toast.LENGTH_SHORT).show();

            return true;
        }
        return false;
    }

    private void register(View view) {

        RetrofitRepository<String> retrofitRepository = new RetrofitRepository<>();

        RegisterRequest registerRequest = new RegisterRequest(
                Objects.requireNonNull(username.getText()).toString(),
                Objects.requireNonNull(password.getText()).toString(),
                Objects.requireNonNull(email.getText()).toString(), "",
                Objects.requireNonNull(description.getText()).toString(),
                Objects.requireNonNull(displayName.getText()).toString(), false);

        retrofitRepository.sendRequest(httpClient.routes.register(registerRequest), view, () -> {

            Toast.makeText(getContext(), "User registration is successful",
                    Toast.LENGTH_LONG).show();

            if(getActivity() != null)
                if(((MainActivity)getActivity()).getMultipartFile() != null)
                    uploadAvatar(((MainActivity)getActivity()).getMultipartFile(), view);
        });


        if(getActivity() != null)
            FragmentTransition.to(PostsFragment.newInstance("hot"), getActivity(),
                    false, R.id.viewPage);

    }

    private void uploadAvatar(MultipartBody.Part multipartFile, View view) {

        RetrofitRepository<AuthResponse> authResponseRetrofitRepository =
                new RetrofitRepository<>();

        LoginRequest loginRequest = new LoginRequest(
                Objects.requireNonNull(username.getText()).toString(),
                Objects.requireNonNull(password.getText()).toString());


        HttpClient uploadHttpClient = new HttpClient();

        authResponseRetrofitRepository.sendRequest(httpClient.routes.login(loginRequest), view,
                () -> {

                    //check if executes

                    String authToken = authResponseRetrofitRepository.getResponseData().
                            getAuthToken();

                    int expiresIn = authResponseRetrofitRepository.getResponseData().
                            getExpiresIn();

                    String username = loginRequest.getUsername();

                    if(getActivity() != null)
                        ((MainActivity)getActivity()).storeDataToSharedPreferences(
                                authToken, expiresIn, username);

                    RetrofitRepository<FileResponse> retrofitRepository = new RetrofitRepository<>();

                    uploadHttpClient.setContext(getContext());

                    if(getActivity() != null) {

                        retrofitRepository.sendRequest(uploadHttpClient.routes.uploadFile(
                                multipartFile), view, () ->
                                Toast.makeText(getActivity(),
                                        "User avatar uploaded successfully",
                                        Toast.LENGTH_SHORT).show());
                    }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
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
        //Toast.makeText(getActivity(), "onDetach()", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();

        Uri uri;

        CircleImageView avatarImage = getView().findViewById(R.id.avatar_image);

        if(getActivity() != null && ((MainActivity)getActivity()).getUri()
                != null) {
            uri = ((MainActivity)getActivity()).getUri();
            avatarImage.setImageURI(uri);
        }
    }
}
