package com.example.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private EditText mEtUserId;
    private Button mBtnCallApi;
    private TextView mTvFirstName;
    private TextView mTvLastName;
    private TextView mTvEmail;
    private ImageView mIvAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiViews();
    }

    private void intiViews() {
        mEtUserId =findViewById(R.id.etUserId);
        mBtnCallApi = findViewById(R.id.btnCallApi);
        mTvFirstName =findViewById(R.id.tvfirstName);
        mTvLastName = findViewById(R.id.tvLastName);
        mTvEmail = findViewById(R.id.tvEmail);
        mIvAvatar = findViewById(R.id.ivAvatar);

        mBtnCallApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiService apiService =Network.getInstance().create(ApiService.class);
                int userId = Integer.parseInt(mEtUserId.getText().toString());
                apiService.getUser(userId).enqueue(new Callback<ResponseDTO>() {
                    @Override
                    public void onResponse(Call<ResponseDTO> call, Response<ResponseDTO> response) {
                        ResponseDTO dto =response.body();
                        String firstName = dto.getData().getFirstName();
                        String lastName =dto.getData().getLastName();
                        String email =dto.getData().getEmail();
                        mTvFirstName.setText(firstName);
                        mTvLastName.setText(lastName);
                        mTvEmail.setText(email);
                        Glide.with(mIvAvatar).load(dto.getData().getAvatar()).into(mIvAvatar);
                    }

                    @Override
                    public void onFailure(Call<ResponseDTO> call, Throwable t) {

                    }
                });
            }
        });
    }
}