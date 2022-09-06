package com.example.sr2_2020_android2021_projekat.fragments;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sr2_2020_android2021_projekat.MainActivity;
import com.example.sr2_2020_android2021_projekat.R;
import com.example.sr2_2020_android2021_projekat.api.RetrofitRepository;
import com.example.sr2_2020_android2021_projekat.model.AuthResponse;
import com.example.sr2_2020_android2021_projekat.model.ChangePasswordRequest;
import com.example.sr2_2020_android2021_projekat.model.FileResponse;
import com.example.sr2_2020_android2021_projekat.model.LoginRequest;
import com.example.sr2_2020_android2021_projekat.model.UserInfoDTO;
import com.example.sr2_2020_android2021_projekat.tools.DialogHelper;
import com.example.sr2_2020_android2021_projekat.tools.EnvironmentConfig;
import com.example.sr2_2020_android2021_projekat.tools.FragmentTransition;
import com.example.sr2_2020_android2021_projekat.tools.HttpClient;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.Objects;

import okhttp3.ResponseBody;

public class ManageAccountFragment extends Fragment {

    public DialogHelper dialogHelper = new DialogHelper();
    private final HttpClient httpClient = new HttpClient();

    private View view;
    private ImageView avatarImage;
    private TextView userKarma;
    private MaterialButton addAvatarButton;
    private MaterialButton changeAvatarButton;
    private MaterialButton removeAvatarButton;
    private TextInputEditText username;
    private TextInputEditText email;
    private TextInputEditText displayName;
    private TextInputEditText description;
    private CheckBox enableFingerprintLoginCheckbox;

    private String avatarMode = "";
    private String filename = null;

    private SharedPreferences.Editor editor;

    public static ManageAccountFragment newInstance() {

        return new ManageAccountFragment();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_manage_account, container, false);

        httpClient.setContext(getContext());

        SharedPreferences preferences = PreferenceManager.
                getDefaultSharedPreferences(getContext());

        editor =  preferences.edit();

        if(getActivity() != null) {
            ((MainActivity)getActivity()).setGroupMenuVisibility(false,
                    false);
            ((MainActivity)getActivity()).setUri(null);
        }

        avatarImage = view.findViewById(R.id.user_avatar_image);
        userKarma = view.findViewById(R.id.user_karma);
        addAvatarButton = view.findViewById(R.id.add_avatar_button);
        changeAvatarButton = view.findViewById(R.id.change_avatar_button);
        removeAvatarButton = view.findViewById(R.id.remove_avatar_button);
        username = view.findViewById(R.id.user_username);
        email = view.findViewById(R.id.user_email);
        displayName = view.findViewById(R.id.user_display_name);
        description = view.findViewById(R.id.user_description);
        enableFingerprintLoginCheckbox = view.findViewById(R.id.enable_fingerprint_checkbox);

        getActivity().setTitle("Manage account");

        ((MainActivity)getActivity()).navigationView.getMenu().
                findItem(R.id.navigation_bar_item_user_manage).setChecked(true);

        getUserInfo();

        ////

        if(preferences.getString("saved_username", null) != null) {
            enableFingerprintLoginCheckbox.setChecked(true);
        }


        ///

        enableFingerprintLoginCheckbox.setOnClickListener(v -> {


            if (((CheckBox) v).isChecked()) {

                dialogHelper.showDialog(getContext(), "Enter password:",
                        R.layout.component_enter_password, () -> {

                            TextInputEditText currentPassword = dialogHelper.getCurrentDialog().
                                    findViewById(R.id.user_current_password);

                            RetrofitRepository<AuthResponse> retrofitRepository =
                                    new RetrofitRepository<>();

                            LoginRequest loginRequest = new LoginRequest(
                                    Objects.requireNonNull(username.getText()).toString(),
                                    Objects.requireNonNull(currentPassword.getText()).toString()
                            );

                            retrofitRepository.sendRequest(httpClient.routes.login(loginRequest),
                                    view, () -> {


                                        editor.putString("saved_username",
                                                loginRequest.getUsername());
                                        editor.putString("saved_password",
                                                loginRequest.getPassword());

                                    });

                        }, () -> {

                        }, () -> {

                        });

            } else {
                editor.putString("saved_username", null);
                editor.putString("saved_password", null);
            }

        });

        Button changePasswordButton = view.findViewById(R.id.change_password_button);

        changePasswordButton.setOnClickListener(v -> {

            dialogHelper.showDialog(getContext(), "Change password:",
                    R.layout.fragment_change_password, () -> {

                            TextInputEditText currentPassword = dialogHelper.getCurrentDialog().
                                    findViewById(R.id.user_current_password);
                            TextInputEditText newPassword = dialogHelper.getCurrentDialog().
                                    findViewById(R.id.user_new_password);
                            TextInputEditText newPasswordRepeated = dialogHelper.getCurrentDialog().
                                    findViewById(R.id.user_new_password_repeated);

                            boolean isCurrentPasswordValid = Objects.requireNonNull(
                                    currentPassword.getText()).toString().length() > 0;
                            boolean isNewPasswordValid = Objects.requireNonNull(
                                    newPassword.getText()).toString().length() > 0;
                            boolean isNewPasswordRepeatedValid = Objects.requireNonNull(
                                    newPasswordRepeated.getText()).toString().length() > 0 &&
                                    Objects.requireNonNull(newPassword.getText()).toString().equals(
                                            newPasswordRepeated.getText().toString());
                            if(!isCurrentPasswordValid) {
                                Toast.makeText(view.getContext(), "Please provide a " +
                                        "valid current password", Toast.LENGTH_SHORT).show();
                            } else if (!isNewPasswordValid)  {
                                Toast.makeText(view.getContext(), "Please provide a " +
                                        "valid new password", Toast.LENGTH_SHORT).show();
                            } else if (!isNewPasswordRepeatedValid) {
                                Toast.makeText(view.getContext(), "New passwords " +
                                        "are not matching", Toast.LENGTH_SHORT).show();
                            } else if (Objects.requireNonNull(
                                    currentPassword.getText()).toString().equals(
                                            Objects.requireNonNull(newPassword.getText()).
                                                    toString())) {
                                Toast.makeText(view.getContext(), "Old and new password " +
                                        "are same", Toast.LENGTH_SHORT).show();
                            } else {
                                RetrofitRepository<ChangePasswordRequest> retrofitRepository = new
                                        RetrofitRepository<>();

                                ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest(
                                        Objects.requireNonNull(currentPassword.getText()).toString(),
                                        Objects.requireNonNull(newPassword.getText()).toString());

                                retrofitRepository.sendRequest(httpClient.routes.changePassword(
                                        changePasswordRequest), view, () -> {

                                    Toast.makeText(view.getContext(), "Password has been " +
                                            "successfully changed", Toast.LENGTH_SHORT).show();

                                    ((MainActivity)getActivity()).logout();
                                });


                            }


                    }, () -> {

                    }, () -> {

                    });



        });

        addAvatarButton.setOnClickListener(v -> {
            avatarMode = "ADD";
            ((MainActivity)getActivity()).openFileChooser();
        });

        changeAvatarButton.setOnClickListener(v -> {
            avatarMode = "CHANGE";
            ((MainActivity)getActivity()).openFileChooser();
        });

        removeAvatarButton.setOnClickListener(v -> {
            avatarMode = "REMOVE";
            avatarImage.setImageResource(R.drawable.ic_avatar);
        });

        MaterialButton saveChangesButton = view.findViewById(R.id.save_changes_button);

        saveChangesButton.setOnClickListener(v -> {

            saveUserDetails(view);

            switch (avatarMode) {
                case "REMOVE":
                    removeAvatar(view);
                    break;

                case "CHANGE":
                    changeAvatar(view);
                    break;

                case "ADD":
                    postAvatar(view);
            }

            editor.apply();

            if(getActivity() != null)
                FragmentTransition.to(PostsFragment.newInstance("hot"), getActivity(),
                        false, R.id.viewPage);

        });


        return view;
    }

    private void changeAvatar(View view) {

        RetrofitRepository<FileResponse> retrofitRepository = new RetrofitRepository<>();

        if(getActivity() != null)
            retrofitRepository.sendRequest(httpClient.routes.changeAvatar(
                    ((MainActivity)getActivity()).getMultipartFile()), view, () -> {});
    }

    private void postAvatar(View view) {

        RetrofitRepository<FileResponse> retrofitRepository = new RetrofitRepository<>();

        if(getActivity() != null)
            retrofitRepository.sendRequest(httpClient.routes.uploadFile(
                ((MainActivity)getActivity()).getMultipartFile()), view, () -> {});
    }

    private void removeAvatar(View view) {

        RetrofitRepository<ResponseBody> retrofitRepository = new RetrofitRepository<>();

        retrofitRepository.sendRequest(httpClient.routes.deleteFile(filename), view,
                () -> {});

        changeAvatarButton.setVisibility(View.GONE);
        removeAvatarButton.setVisibility(View.GONE);
    }

    private void saveUserDetails(View view) {

        RetrofitRepository<String> retrofitRepository = new RetrofitRepository<>();

        UserInfoDTO userInfoDTO = new UserInfoDTO(
                Objects.requireNonNull(displayName.getText()).toString(),
                Objects.requireNonNull(description.getText()).toString()
        );

        retrofitRepository.sendRequest(httpClient.routes.updateAccountInfo(userInfoDTO), view,
                () -> Toast.makeText(getContext(), "Account changes are successfully saved",
                        Toast.LENGTH_LONG).show());

    }

    private void getUserInfo() {

        RetrofitRepository<UserInfoDTO> retrofitRepository = new RetrofitRepository<>();

        retrofitRepository.sendRequest(httpClient.routes.getAccountInfo(), view, () -> {

            UserInfoDTO userInfoDTO = retrofitRepository.getResponseData();

            if(userInfoDTO.getAvatar() != null) {

                filename = userInfoDTO.getAvatar();

                String imageUrl = EnvironmentConfig.baseURL + "file/" +
                        userInfoDTO.getAvatar();

                Picasso.get().load(imageUrl).into(avatarImage);

            } else {
                changeAvatarButton.setVisibility(View.GONE);
                removeAvatarButton.setVisibility(View.GONE);
                addAvatarButton.setVisibility(View.VISIBLE);
            }

            String karma = "Karma: "  + userInfoDTO.getKarma();

            userKarma.setText(karma);
            username.setText(userInfoDTO.getUsername());
            email.setText(userInfoDTO.getEmail());
            displayName.setText(userInfoDTO.getDisplayName());
            description.setText(userInfoDTO.getDescription());

        });
    }

    @Override
    public void onResume() {
        super.onResume();

        Uri uri;

        if(getActivity() != null && ((MainActivity)getActivity()).getUri()
                != null) {
            uri = ((MainActivity)getActivity()).getUri();
            avatarImage.setImageURI(uri);
        }

    }
}