package com.example.limeapp.Core;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.limeapp.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    FirebaseDatabase db;
    private CircleImageView circleImageView;
    private Uri imageUri;
    private String myUri = "";
    private StorageTask uploadTask;
    private StorageReference storageProfileReference;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        db = FirebaseDatabase.getInstance();
        DatabaseReference users = db.getReference("Client");
        String userId = user.getUid();


        setContentView(R.layout.profile_activity);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getWindow().setStatusBarColor(getResources().getColor(R.color.LogCol));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.LogCol));

        ImageView nav_verefication = findViewById(R.id.nav_verefication);
        ImageView nav_buy = findViewById(R.id.nav_buy);
        ImageView nav_first = findViewById(R.id.nav_firstScreen);


        ImageView LogOut = findViewById(R.id.imageView36);
        circleImageView = findViewById(R.id.profile_image);
        ImageView addImage = findViewById(R.id.imageView34);
        ImageView profBut = findViewById(R.id.profile_imagePic);


        storageProfileReference = FirebaseStorage.getInstance().getReference().child("Profile Pic");


        profBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CropImage.activity().setAspectRatio(1, 1).start(ProfileActivity.this);
                    uploadProfileImage();
                } catch (Exception e) {
                    e.printStackTrace();
                    circleImageView.setVisibility(View.INVISIBLE);
                    profBut.setVisibility(View.VISIBLE);
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                addImage.setVisibility(View.VISIBLE);
                profBut.setVisibility(View.INVISIBLE);
                circleImageView.setVisibility(View.VISIBLE);
            }
        });

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    CropImage.activity().setAspectRatio(1, 1).start(ProfileActivity.this);
                    uploadProfileImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                addImage.setVisibility(View.VISIBLE);
            }
        });
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadProfileImage();
                addImage.setVisibility(View.INVISIBLE);
            }
        });


        users.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if (snapshot.hasChild("image")) {
                    String image = snapshot.child("image").getValue().toString();
                    try {
                        Picasso.get().load(image).into(circleImageView);
                        profBut.setVisibility(View.INVISIBLE);
                        circleImageView.setVisibility(View.VISIBLE);
                    } catch (Exception e) {

                    }

                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        LogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                toLogin();
            }
        });

        /*PriseBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPrise();
            }
        });
        DocBut.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                uploadProfileImage();
                HashMap<String,Object> j  = new HashMap<>();
                j.put("name",nameEdit.getText().toString());
                users.child(auth.getCurrentUser().getUid()).updateChildren(j);
                toMainScreen();
            }
        });*/
        nav_verefication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMainScreen();
            }
        });
        nav_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPrise();
            }
        });
        nav_first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadProfileImage();
                toFirst();
            }
        });

    }

    void toMainScreen() {
        Intent intent = new Intent(this, VerificationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }

    void toLogin() {
        Intent intent = new Intent(this, LogActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }

    void toPrise() {
        Intent intent = new Intent(this, BuyScreen.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }

    void toFirst() {
        Intent intent = new Intent(this, FirstScreen.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anime_in, R.anim.anime_out);
    }

    void uploadProfileImage() {

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        db = FirebaseDatabase.getInstance();
        DatabaseReference users = db.getReference().child("Client");
        String userId = user.getUid();


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Set your profile");
        progressDialog.setMessage("Please wait");
        progressDialog.show();

        if (imageUri != null) {
            final StorageReference fileRef = storageProfileReference.child(auth.getCurrentUser().getUid() + ".jpg");
            uploadTask = fileRef.putFile(imageUri);

            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull Task task) throws Exception {
                    if (!task.isSuccessful()) {
                        task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downloadUrl = task.getResult();
                        myUri = downloadUrl.toString();

                        HashMap<String, Object> userMap = new HashMap<>();
                        userMap.put("image", myUri);
                        users.child(auth.getCurrentUser().getUid()).updateChildren(userMap);
                        progressDialog.dismiss();
                    }
                }
            });
        } else {
            progressDialog.dismiss();
            Toast toast = Toast.makeText(ProfileActivity.this, "Не вибрано фото", Toast.LENGTH_LONG);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {


            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();

            circleImageView.setImageURI(imageUri);

            Toast.makeText(ProfileActivity.this, "Помилка", Toast.LENGTH_LONG);

        } catch (Exception e) {

        }
    }
}
