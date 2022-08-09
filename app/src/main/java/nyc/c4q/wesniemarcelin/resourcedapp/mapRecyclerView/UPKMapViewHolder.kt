package nyc.c4q.wesniemarcelin.resourcedapp.mapRecyclerView

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import nyc.c4q.wesniemarcelin.resourcedapp.model.Rows
import nyc.c4q.wesniemarcelin.resourcedapp.R

/**
 * Created by wesniemarcelin on 2/3/17.
 */
class UPKMapViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var location: TextView
    var address: TextView
    var phone: TextView
    var borough: TextView
    fun bind(rows: Rows) {
        location.text = rows.locname
        if (rows.address == null) {
            address.text = "Unknown Address"
        } else {
            address.text = rows.address.toString()
        }
        borough.text = rows.borough + ", NY " + rows.zip
        phone.text = "Phone: " + rows.phone
        itemView.setOnClickListener {
            //Write what to do when item is clicked in RV
        }
    }

    //    RecyclerView recyclerView;
    init {
        //        recyclerView = (RecyclerView)view.findViewById(R.id.map_recycler_view);
        location = itemView.findViewById<View>(R.id.location) as TextView
        address = itemView.findViewById<View>(R.id.address) as TextView
        phone = itemView.findViewById<View>(R.id.phone) as TextView
        borough = itemView.findViewById<View>(R.id.borough) as TextView
    }
}