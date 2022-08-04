package nyc.c4q.wesniemarcelin.resourcedapp.mapRecyclerView;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nyc.c4q.wesniemarcelin.resourcedapp.R;
import nyc.c4q.wesniemarcelin.resourcedapp.model.Rows;

/**
 * Created by wesniemarcelin on 2/3/17.
 */

public class UPKMapAdapter extends RecyclerView.Adapter<UPKMapViewHolder> {
    List<Rows> mUPKList;

    public UPKMapAdapter(List<Rows> mUPKList) {
        this.mUPKList = mUPKList;
    }

    //    List<Favorites> mFavList;
    @Override
    public UPKMapViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.upk_item_layout, parent, false);
        return new UPKMapViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UPKMapViewHolder holder, int position) {
        Rows rows = mUPKList.get(position);
        holder.bind(rows);

    }

    @Override
    public int getItemCount() {
        return mUPKList.size();
    }
}
