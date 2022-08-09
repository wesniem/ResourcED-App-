package nyc.c4q.wesniemarcelin.resourcedapp.mapRecyclerView

import nyc.c4q.wesniemarcelin.resourcedapp.model.Rows
import androidx.recyclerview.widget.RecyclerView
import nyc.c4q.wesniemarcelin.resourcedapp.mapRecyclerView.UPKMapViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import nyc.c4q.wesniemarcelin.resourcedapp.R

/**
 * Created by wesniemarcelin on 2/3/17.
 */
class UPKMapAdapter(var mUPKList: List<Rows>) : RecyclerView.Adapter<UPKMapViewHolder>() {
    //    List<Favorites> mFavList;
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UPKMapViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.upk_item_layout, parent, false)
        return UPKMapViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UPKMapViewHolder, position: Int) {
        val rows = mUPKList[position]
        holder.bind(rows)
    }

    override fun getItemCount(): Int {
        return mUPKList.size
    }
}