package fr.wcs.atelierfirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mUsersDatabaseReference;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firebase Start
        mDatabase = FirebaseDatabase.getInstance();
        mUsersDatabaseReference = mDatabase.getReference().child("Users");

        //Init ID
        final EditText user = (EditText) findViewById(R.id.editText_user);
        final EditText password = (EditText) findViewById(R.id.editText_password);
        final EditText score = (EditText) findViewById(R.id.editText_score);
        Button buttonSendDB = (Button) findViewById(R.id.button_send);

        buttonSendDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((user.getText().toString().trim().length() == 0) &&
                        (score.getText().toString().trim().length() == 0) &&
                        (password.getText().toString().trim().length() == 0)) {
                    Toast.makeText(MainActivity.this, "Please put a name and a score", Toast.LENGTH_SHORT).show();
                } else {

                    //Create player from object
                    final PlayerModel newPlayer = new PlayerModel(user.getText().toString(),
                            password.getText().toString(),
                            Integer.parseInt(score.getText().toString()));

                    //Send newPlayer to DB
                    mUsersDatabaseReference = mDatabase.getReference().child("Users");
                    uid = mUsersDatabaseReference.push().getKey();
                    mUsersDatabaseReference.child(uid).setValue(newPlayer);

                    startActivity(new Intent(MainActivity.this, BestPlayerActivity.class));
                }
            }
        });
    }
}
