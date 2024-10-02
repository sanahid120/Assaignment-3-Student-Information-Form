package com.example.assaignment3;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText, idEditText, emailEditText, phoneEditText, passwordEditText;
    private Spinner deptSpinner;
    private String name, id, email, phone, dept, password;
    private Button submit, clear;
    LinearLayout inputLayout, outputLayout;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameEditText = findViewById(R.id.name);
        idEditText = findViewById(R.id.sId);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.num);
        deptSpinner = findViewById(R.id.spinner);
        passwordEditText = findViewById(R.id.pass);
        submit = findViewById(R.id.submit);
        clear = findViewById(R.id.btn_clear);
        inputLayout = findViewById(R.id.inputLayout);
        outputLayout = findViewById(R.id.outputLayout);
        outputText = findViewById(R.id.outputText);

        String[] items = new String[]{"Select Department", "CSE", "EEE", "ARCH", "CE", "BuA", "ENG", "LAW", "IS", "BNG", "THM", "PH"};
        deptSpinner.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, items));
        deptSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dept = deptSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputLayout.setVisibility(View.VISIBLE);
                outputLayout.setVisibility(View.GONE);
                clearAllFields();
            }
        });

        addTextChangeListeners();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameEditText.getText().toString().trim();
                id = idEditText.getText().toString().trim();
                email = emailEditText.getText().toString().trim();
                phone = phoneEditText.getText().toString().trim();
                password = passwordEditText.getText().toString().trim();

                if (validateInputs()) {
                    inputLayout.setVisibility(View.GONE);
                    outputLayout.setVisibility(View.VISIBLE);
                    String output = "Name: " + name + "\nS Id: " + id + "\nEmail: " + email + "\nMobile Number: " + phone + "\nDepartment: " + dept;
                    outputText.setText(output);
                }
            }
        });
    }

    private boolean validateInputs() {
        boolean isValid = true;

        if (!validateName(name)) {
            nameEditText.setBackgroundResource(R.drawable.error_edit_text);
            isValid = false;
        }
        if (!validateID(id)) {
            idEditText.setBackgroundResource(R.drawable.error_edit_text);
            isValid = false;
        }
        if (!validateEmail(email)) {
            emailEditText.setBackgroundResource(R.drawable.error_edit_text);
            isValid = false;
        }
        if (!validatePhone(phone)) {
            phoneEditText.setBackgroundResource(R.drawable.error_edit_text);
            isValid = false;
        }

        if (!validatePassword(password)) {
            passwordEditText.setBackgroundResource(R.drawable.error_edit_text);
            isValid = false;
        }

        return isValid;
    }

    private boolean validateName(String name) {
        Pattern namePattern = Pattern.compile("^[A-Za-z]+(?: [A-Za-z]+)*$");
        return namePattern.matcher(name).matches();
    }

    private boolean validateID(String id) {
        Pattern idPattern = Pattern.compile("^\\d{16}$");
        return idPattern.matcher(id).matches();
    }

    private boolean validateEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        return emailPattern.matcher(email).matches();
    }

    private boolean validatePhone(String phone) {
        Pattern phonePattern = Pattern.compile("^(\\+88)?01[2-9][0-9]{8}$");
        return phonePattern.matcher(phone).matches();
    }

    private boolean validatePassword(String password) {
        Pattern passwordPattern = Pattern.compile("^(?=.{8,})(?=.*[A-Za-z0-9])(?:(?=.*[.@#&%])[A-Za-z0-9.@#&%]*|[A-Za-z0-9]{8,})$");
        return passwordPattern.matcher(password).matches();
    }

    private void addTextChangeListeners() {
        nameEditText.addTextChangedListener(createTextWatcher(nameEditText));
        idEditText.addTextChangedListener(createTextWatcher(idEditText));
        emailEditText.addTextChangedListener(createTextWatcher(emailEditText));
        phoneEditText.addTextChangedListener(createTextWatcher(phoneEditText));
        passwordEditText.addTextChangedListener(createTextWatcher(passwordEditText));
    }

    private TextWatcher createTextWatcher(final EditText editText) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText.setBackgroundResource(R.drawable.normal_edit_text);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
    }

    private void clearAllFields() {
        nameEditText.setText("");
        idEditText.setText("");
        emailEditText.setText("");
        phoneEditText.setText("");
        passwordEditText.setText("");

    }


}
