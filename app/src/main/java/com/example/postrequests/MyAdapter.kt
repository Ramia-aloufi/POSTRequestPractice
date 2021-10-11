package com.example.postrequests
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.userlist.view.*


class MyAdapter( var item:ArrayList<Users.UserData>):RecyclerView.Adapter<MyAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.userlist,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var item1 = item[position].pk
        var item2 = item[position].name
        var item3 = item[position].location
        holder.itemView.apply {
            textView.text = "$item1\n$item2\n$item3\n\n"
        }
    }

    override fun getItemCount() = item.size
}