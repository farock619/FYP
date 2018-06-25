package hextogen.daurm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class teacher_panel extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_panel);

        mAuth = FirebaseAuth.getInstance();
    }


    public void gotoLock(View view) {

        startActivity(new Intent(teacher_panel.this, lock_panel.class));
        finish();
    }

    public void signout(View view) {

        mAuth.signOut();
        startActivity(new Intent(teacher_panel.this, teacher_loginn.class));
        finish();


    }
}
