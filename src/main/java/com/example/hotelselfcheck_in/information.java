package com.example.hotelselfcheck_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class information extends AppCompatActivity {

     private Button submit;
     private ImageButton upload;
     private TextInputEditText nametext,phonetext,aadhaar,addresstext,namestext ;
     private TextInputLayout name,number,np,address,names;
    private static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        namestext=findViewById(R.id.namestext);
        phonetext=findViewById(R.id.phonetext);
        aadhaar=findViewById(R.id.aadhaar);
        //addresstext=findViewById(R.id.addtext);
        nametext=findViewById(R.id.nameText);

        names=findViewById(R.id.names);
        number=findViewById(R.id.number);
        np=findViewById(R.id.num_people);
        address=findViewById(R.id.address);
        name=findViewById(R.id.name);

        nametext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String nm=charSequence.toString();
                if(nm.length()>=1){
                    name.setHelperTextEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        phonetext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String nmu=charSequence.toString();
                if(nmu.length()>=10){
                    number.setHelperText("");
                }
                else{
                    number.setHelperText("Invaid");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        aadhaar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String nmd=charSequence.toString();
                if(nmd.length()>=12){
                    np.setHelperText("");
                }
                else{
                    np.setHelperText("Invaid");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        upload=findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,PICK_IMAGE);
                //upload code
            }
        });


        submit=findViewById(R.id.button3);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nam,pt,at;
                nam=nametext.getText().toString();
                pt=phonetext.getText().toString();
                at=aadhaar.getText().toString();
                if(nam.length()>=1 && pt.length()>=1 && at.length()>=1){
                    Intent i = new Intent(getApplicationContext(),room_info.class);
                    startActivity(i);
                }
                else{
                    Snackbar snackbar= Snackbar.make(view," Please Fill The Above Details ",Snackbar.LENGTH_LONG);
                    snackbar.setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE);
                    snackbar.setAction("OKAY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //for highlight the required part
                        }
                    });
                    snackbar.show();
                    //Toast toast =Toast.makeText(getApplicationContext(),"FILL THE ABOVE DETALS",Toast.LENGTH_LONG);
                    //toast.show();
                }
            }
        });

    }
}