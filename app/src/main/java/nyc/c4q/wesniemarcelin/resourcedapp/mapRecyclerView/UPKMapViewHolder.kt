package nyc.c4q.wesniemarcelin.resourcedapp.mapRecyclerView;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import nyc.c4q.wesniemarcelin.resourcedapp.R;
import nyc.c4q.wesniemarcelin.resourcedapp.model.Rows;

/**
 * Created by wesniemarcelin on 2/3/17.
 */

public class UPKMapViewHolder extends RecyclerView.ViewHolder {
    TextView location;
    TextView address;
    TextView phone;
    TextView borough;
//    RecyclerView recyclerView;

    public UPKMapViewHolder(View itemView) {
        super(itemView);
//        recyclerView = (RecyclerView)view.findViewById(R.id.map_recycler_view);
        location = (TextView) itemView.findViewById(R.id.location);
        address = (TextView) itemView.findViewById(R.id.address);
        phone = (TextView) itemView.findViewById(R.id.phone);
        borough = (TextView) itemView.findViewById(R.id.borough);
    }

    public void bind(final Rows rows) {
        location.setText(rows.getLocname());
        if (rows.getAddress() == null) {
            address.setText("Unknown Address");
        } else {
            address.setText(rows.getAddress().toString());
        }
        borough.setText(rows.getBorough() + ", NY " + rows.getZip());
        phone.setText("Phone: " + rows.getPhone());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//Write what to do when item is clicked in RV
            }
        });
    }
}
