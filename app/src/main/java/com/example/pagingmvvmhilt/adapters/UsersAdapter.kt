package com.example.pagingmvvmhilt.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pagingmvvmhilt.R
import com.example.pagingmvvmhilt.models.model_users.Data
import com.mikhaellopez.circularimageview.CircularImageView


class UsersAdapter(private val context: Context, private val list: MutableList<Data>) :
    RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        return UsersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rcv_users_holder, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val list = list[position]
        holder.txtId.text = list.id.toString()
        holder.txtEmail.text = list.email
        holder.txtFirstName.text = list.first_name
        holder.txtLastName.text = list.last_name
        Glide
            .with(context)
            .load(list.avatar)
            .into(holder.imgUser)
    }

    fun addItems(oldCount: Int, results: List<Data>) {
        list.addAll(results)
        notifyItemRangeInserted(oldCount, results.size)
    }

    class UsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtId: TextView = itemView.findViewById(R.id.txtId)
        val txtEmail: TextView = itemView.findViewById(R.id.txtEmail)
        val txtFirstName: TextView = itemView.findViewById(R.id.txtFirstName)
        val txtLastName: TextView = itemView.findViewById(R.id.txtLastName)
        val imgUser: CircularImageView = itemView.findViewById(R.id.imgUser)
    }

}