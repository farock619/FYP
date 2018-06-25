package hextogen.daurm;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class student_signup extends AppCompatActivity {


    private FirebaseAuth mAuth;
    EditText sname,mail, rnum, pass, cpass, dept;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);
        mAuth = FirebaseAuth.getInstance();

        mail  = findViewById(R.id.ss_email);
        sname = findViewById(R.id.ss_name);
        rnum  = findViewById(R.id.ss_rollnumber);
        pass  = findViewById(R.id.ss_pass);
        cpass = findViewById(R.id.ss_cpass);
        dept  = findViewById(R.id.ss_dept);

    }

    public void createAccount(View view) {


        String email   = mail.getText().toString();
        String name    = sname.getText().toString();
        String rolln   = rnum.getText().toString();
        String passw   = pass.getText().toString();
        String cpassw  = cpass.getText().toString();
        String depart  = dept.getText().toString();

        if(email.length()==0&&email.length()<8||name.length()==0&&name.length()<8||
                rolln.length()==0&&rolln.length()<8||passw.length()==0&&passw.length()<8
                ||cpassw.length()==0&&cpassw.length()<8||depart.length()==0&&depart.length()<8){

            Toast.makeText(this,"Enter in all fields", Toast.LENGTH_SHORT).show();
        }

        if(emailValidator(email)!=true){
            Toast.makeText(student_signup.this, "Domain must be @uogsialkot.edu.pk", Toast.LENGTH_SHORT).show();
        }

        if(rollnumberValidator(rolln)!=true){

            Toast.makeText(student_signup.this, "Enter Valid Roll number", Toast.LENGTH_SHORT).show();
        }

        else  if(passw.equals(cpassw)!=true){

            Toast.makeText(this,"Passwords do not match", Toast.LENGTH_SHORT).show();
        }

        else{

            mAuth.createUserWithEmailAndPassword(email, passw)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.e("create", "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(student_signup.this, "Signed up Successfully",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(student_signup.this, teacher_loginn.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.e("create", "createUserWithEmail:failure", task.getException());
                                Toast.makeText(student_signup.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }

                            // ...
                        }
                    });
        }


    }

    private boolean rollnumberValidator(String rolln) {

        if(rolln.length()==11){
            return true;
        }
        else
            return false;
    }
    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\\\.[_A-Za-z0-9-]+)*@uogsialkot.edu.pk";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }



}
