package com.all_the_best.knock_knock.infant_layout.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.all_the_best.knock_knock.R
import com.all_the_best.knock_knock.infant_layout.model.InfantDecoItemChar

class InfantDecoRcvAdapter(var context: Context?, var arrayList: ArrayList<InfantDecoItemChar>):RecyclerView.Adapter<InfantDecoRcvAdapter.ItemHolder>() {
    //
    //    // Provide a reference to the views for each data item
    //    // Complex data items may need more than one view per item, and
    //    // you provide access to all the views for a data item in a view holder.
    // Each data item is just a string in this case that is shown in a TextView.

    class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemimage: ImageView = itemView.findViewById(R.id.item_image)

    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemHolder {
        // create a new view
        val itemHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.rcv_infant_grid_layout_list_item, parent, false)

        return ItemHolder(itemHolder)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        var DecoChar: InfantDecoItemChar = arrayList.get(position)

        holder.itemimage.setImageResource(DecoChar.iconsChar!!)

       // holder.itemimage.setImageResource(R.drawable.img_infant_itembox_false_rcv)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return arrayList.size
    }
}