package riksasuviana.apps.foodreservation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.rv) RecyclerView rv;
    List<Restaurant> resList = new ArrayList<>();
    MyAdapter adp;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        ref = FirebaseDatabase.getInstance().getReference().child("timelines");

        adp = new MyAdapter(resList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);
        rv.setAdapter(adp);
        getData();

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getTimelines(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getTimelines(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getTimelines(DataSnapshot ds){
        for(DataSnapshot singleSnapshot : ds.getChildren()){
            String foto = singleSnapshot.child("foto").getValue(String.class);
            String nama = singleSnapshot.child("nama").getValue(String.class);
            Restaurant r = new Restaurant(foto, nama);
            resList.add(r);
        }
        adp.notifyDataSetChanged();
    }

    public void getData(){
        Restaurant r = new Restaurant("Foto", "Judul");
        resList.add(r);

        r = new Restaurant("Fotokedua", "Judulnyakedua");
        resList.add(r);

        adp.notifyDataSetChanged();
    }
}
