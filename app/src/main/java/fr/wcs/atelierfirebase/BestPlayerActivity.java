package fr.wcs.atelierfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BestPlayerActivity extends AppCompatActivity {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mUsersDatabaseReference;
    private String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_best_player);

        //Firebase Start
        mDatabase = FirebaseDatabase.getInstance();
        mUsersDatabaseReference = mDatabase.getReference().child("Users");

        //Init ID
        final TextView bestPlayer = (TextView) findViewById(R.id.textView_MVP);

        mUsersDatabaseReference.orderByChild("Score").limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot usersSnapshot : dataSnapshot.getChildren()) {
                    PlayerModel MVP =
                            usersSnapshot.getValue(PlayerModel.class);
                    bestPlayer.setText("Joueur : " + MVP.getPlayer() + " // score : " + MVP.getScore());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}
