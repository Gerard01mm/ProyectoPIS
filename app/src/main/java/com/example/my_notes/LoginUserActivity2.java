package com.example.my_notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.regex.Pattern;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUserActivity2 extends AppCompatActivity implements View.OnClickListener{

    //Constants
    private final String LOGIN_ERROR = "Name, email or password are not correct";
    private final String EMPTY_INPUT = "Some of the text areas are empty";

    private TextInputEditText name, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user2);

        findViewById(R.id.next_button).setOnClickListener(this);
        findViewById(R.id.label_signin2).setOnClickListener(this);

        this.name = (TextInputEditText) findViewById(R.id.name_edit_text);
        this.email = (TextInputEditText) findViewById(R.id.email_edit_text);
        this.password = (TextInputEditText) findViewById(R.id.password_edit_text);

    }

    /*Metodo para iniciar la activity de registrarse*/
    private void goToRegisterUserActivity(){
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }


    /**
     * Funció que permet passar del loguin a la activitat principal.
     */
    private void goToMainActivity(){
        Intent intent2 = new Intent(this, MainActivity.class);
        intent2.putExtra("usuario", name.getText().toString());
        intent2.putExtra("email", email.getText().toString());
        startActivity(intent2);
    }

    @Override
    public void onClick(View view) {

        //En cas que pulsem el botond e continue
        if (R.id.next_button == view.getId()){

            //En cas que els tres camps estiguin omplerts
            if (!this.email.getText().toString().isEmpty() && !this.password.getText().toString().isEmpty() && !this.name.getText().toString().isEmpty()){

                //Busquem un usuari amb el email i constrasenya introduits.
                FirebaseAuth.getInstance().
                        signInWithEmailAndPassword(this.email.getText().toString(),
                                this.password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //En cas que trobem l'usuari
                        if (task.isSuccessful()){
                            goToMainActivity();
                        }

                        //En qualsevol altre cas
                        else{
                            showErrorMessage(LOGIN_ERROR);
                        }
                    }
                });
            }

            //En cas que algun camp de text es trobi buit.
            else{
                showErrorMessage(EMPTY_INPUT);
            }
        }

        //En cas que pulsem l'etiqueta de signIn
        if (R.id.label_signin2 == view.getId()){
            goToRegisterUserActivity();
        }
    }



    private void showErrorMessage(String error){
        Toast.makeText(LoginUserActivity2.this, error, Toast.LENGTH_SHORT).show();
    }

}