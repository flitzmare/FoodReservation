package riksasuviana.apps.foodreservation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by User on 1/13/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private List<Restaurant> resList;

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView photo, name;

        public ViewHolder(View view){
            super(view);
            photo = (TextView)view.findViewById(R.id.photo);
            name = (TextView)view.findViewById(R.id.name);
        }
    }

    public MyAdapter(List<Restaurant> resList){
        this.resList = resList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardres, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Restaurant r = resList.get(position);
        holder.photo.setText(r.getPhoto());
        holder.name.setText(r.getName());
    }

    @Override
    public int getItemCount() {
        return resList.size();
    }
}
