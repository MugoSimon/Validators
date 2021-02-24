package lastie_wangechian_validators.com;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    //static variables
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +   //atleast one digit
                    "(?=.*[a-z])" +   //atleast one letter either upercase ama lowercase
                    "(?=\\S+$)" +     //no whitespace in the password
                    "(?=.*[@#&*^+$])" +  // atleast one special characters
                    ".{6,}" +         //minimum of 6 characters
                    "$");
    //variables layouts from the widgets
    private Button button_submit;
    private TextInputLayout textInputLayout_name, textInputLayout_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing the widgets
        button_submit = findViewById(R.id.button_submit);
        textInputLayout_name = findViewById(R.id.textField_name);
        textInputLayout_password = findViewById(R.id.textField_password);

        //when button clicked
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    if ( !validateName() | !validatePassword()) {

                        return;

                    } else {

                        Toast.makeText(MainActivity.this, "Things are alright", Toast.LENGTH_LONG).show();
                    }

                } catch (RuntimeException exception) {

                    Toast.makeText(MainActivity.this,exception.getMessage().toString(),Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    //boolean method to validate name
    private boolean validateName() {

        String name = textInputLayout_name.getEditText().getText().toString().trim();
        if (TextUtils.isEmpty(name)) {

            textInputLayout_name.requestFocus();
            textInputLayout_name.setError("field cannot be left empty");
            textInputLayout_name.getEditText().setText(null);
            return false;

        } else if (name.length() > 15) {

            textInputLayout_name.requestFocus();
            textInputLayout_name.setError("name is too long");
            textInputLayout_name.getEditText().setText(null);
            return false;

        } else {

            textInputLayout_name.setError(null);
            return true;
        }

    }

    //boolean method to validate password
    private boolean validatePassword() {

        String password = textInputLayout_password.getEditText().getText().toString().trim();
        if (TextUtils.isEmpty(password)) {

            textInputLayout_password.requestFocus();
            textInputLayout_password.setError("field cannot be left empty");
            textInputLayout_password.getEditText().setText(null);
            return false;

        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {

            textInputLayout_password.requestFocus();
            textInputLayout_password.getEditText().setText(null);
            textInputLayout_password.setError("password too weak");
            return false;

        } else {

            textInputLayout_password.setError(null);
            return true;
        }
    }
}