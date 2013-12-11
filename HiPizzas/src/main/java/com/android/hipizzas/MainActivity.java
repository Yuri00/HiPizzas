package com.android.hipizzas;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    DBAdapter dbAdapter;
    EditText txtUserName;
    EditText txtPassword;
    Button btnLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUserName = (EditText)findViewById(R.id.et_user);
        txtPassword = (EditText)findViewById(R.id.et_pw);
        btnLogin = (Button)findViewById(R.id.btn_login);
        btnRegister = (Button)findViewById(R.id.btn_reg);

        dbAdapter = new DBAdapter(this);
        dbAdapter.open();

        btnLogin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txtUserName.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(txtPassword.getWindowToken(), 0);
                String username = txtUserName.getText().toString();
                String password = txtPassword.getText().toString();
                if (username.length() > 0 && password.length() > 0) {
                    try {

                        if (dbAdapter.Login(username, password)) {
                            Toast.makeText(MainActivity.this,
                                    "Login efetuado com sucesso", Toast.LENGTH_LONG)
                                    .show();
                        } else {
                            Toast.makeText(MainActivity.this,
                                    "Usúario ou senha inválidos",
                                    Toast.LENGTH_LONG).show();
                        }

                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Ocorreu algum erro",
                                Toast.LENGTH_LONG).show();

                    }
                } else {
                    Toast.makeText(MainActivity.this,
                            "Usúario ou senha vazios", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnRegister.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(txtUserName.getWindowToken(), 0);
                imm.hideSoftInputFromWindow(txtPassword.getWindowToken(), 0);
                try {

                    String username = txtUserName.getText().toString();
                    String password = txtPassword.getText().toString();
                    long i = dbAdapter.autenticar(username, password);
                    if(i != -1)
                        Toast.makeText(MainActivity.this, "Cadastro Realizado com sucesso",Toast.LENGTH_LONG).show();

                } catch (SQLException e) {
                    Toast.makeText(MainActivity.this, "Ocorreu algum problema",
                            Toast.LENGTH_LONG).show();

                }

            }
        });
    }
}
