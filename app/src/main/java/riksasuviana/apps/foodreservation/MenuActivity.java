package riksasuviana.apps.foodreservation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {

    String thi;

    @BindView(R.id.tvr)
    TextView tvr;
    @BindView(R.id.rv) RecyclerView rv;
    List<Restaurant> resList = new ArrayList<>();
    MyAdapter adp;
    DatabaseReference ref;

    @OnClick(R.id.btn) void btn(){
        Restaurant r = new Restaurant("photopushed", "namepushed");
        ref.push().setValue(r);
    }
    @OnClick(R.id.btn2) void btn2(){
        ref.setValue("button2");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        ref = FirebaseDatabase.getInstance().getReference().child("timelines");

        adp = new MyAdapter(resList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setLayoutManager(lm);

        FirebaseRecyclerAdapter<Restaurant, MessageViewHolder> adapter = new FirebaseRecyclerAdapter<Restaurant, MessageViewHolder>(
                Restaurant.class,
                R.layout.cardres,
                MessageViewHolder.class,
                ref
        ){
            protected void populateViewHolder(MessageViewHolder viewHolder, Restaurant model, final int position){
                viewHolder.setPhoto(getApplicationContext(), model.getPhoto());
                viewHolder.setName(model.getName());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DatabaseReference clickref = getRef(position);
                        String postkey = clickref.getKey();
                        Intent i = new Intent(MenuActivity.this, ResprofileActivity.class);
                        i.putExtra("key", postkey);
                        startActivity(i);
                    }
                });
            }
        };
        rv.setAdapter(adapter);
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public MessageViewHolder(View v){
            super(v);
            mView = v;
        }

        public void setPhoto(Context c, String photo){
//            TextView t = (TextView)mView.findViewById(R.id.photo);
//            t.setText(photo);
            ImageView t = (ImageView)mView.findViewById(R.id.photo);
            Glide.with(c).load(photo).centerCrop().into(t);
        }
        public void setName(String name){
            TextView t = (TextView)mView.findViewById(R.id.name);
            t.setText(name);
        }
    }

//    public void getTimelines(DataSnapshot ds){
////        tvr.setText("key : "+ds.getKey()+" value : "+ds.getValue());
//        for(DataSnapshot singleSnapshot : ds.getChildren()){
//            String key = singleSnapshot.getValue(String.class);
//        }
//
//        adp.notifyDataSetChanged();
//    }

//    public void getData(){
//        Restaurant r = new Restaurant("Foto", "Judul");
//        resList.add(r);
//
//        r = new Restaurant("Fotokedua", "Judulnyakedua");
//        resList.add(r);
//
//        adp.notifyDataSetChanged();
//    }
}
