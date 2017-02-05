package nyc.c4q.wesniemarcelin.resourcedapp.mapRecyclerView;

import android.support.v7.widget.RecyclerView;
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
    TextView zip;
    TextView phone;
    TextView borough;
    TextView prekType;
    TextView seats;
    TextView dayLength;
//    RecyclerView recyclerView;

    public UPKMapViewHolder(View itemView) {
        super(itemView);
//        recyclerView = (RecyclerView)view.findViewById(R.id.map_recycler_view);
        location = (TextView) itemView.findViewById(R.id.location);
        address = (TextView) itemView.findViewById(R.id.address);
        zip = (TextView) itemView.findViewById(R.id.zip);
        phone = (TextView) itemView.findViewById(R.id.phone);
        borough = (TextView) itemView.findViewById(R.id.borough);
        prekType = (TextView) itemView.findViewById(R.id.prek_type);
        seats = (TextView) itemView.findViewById(R.id.seats);
        dayLength = (TextView) itemView.findViewById(R.id.day_length);


    }

    public void bind(final Rows rows) {
        location.setText(rows.getLocname());
        if (rows.getAddress() == null) {
            address.setText("None");
        } else {
            address.setText(rows.getAddress().toString());
        }
        zip.setText(rows.getZip());
        phone.setText(rows.getPhone());
        borough.setText(rows.getBorough());
        prekType.setText(rows.getPrektype());
        seats.setText(rows.getSeats());
        dayLength.setText(rows.getDaylength());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//Write what to do when item is clicked in RV
            }
        });
    }
}
