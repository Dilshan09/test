package com.example.mad

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityManager.AccessibilityServicesStateChangeListener
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class addjob_Addapter ( val userList : ArrayList<retrive_job>) : RecyclerView.Adapter<addjob_Addapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener


    //interface for the click event
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }


    //set the click listner for the item click events
    fun setOnItemClickListener(clickListener : onItemClickListener){
        mListener = clickListener
    }

    class MyViewHolder(itemView : View, clickListener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val Name : TextView = itemView.findViewById(R.id.comnama)  //records card displaying things
        val Mail : TextView = itemView.findViewById(R.id.posnama)
        val Tele : TextView = itemView.findViewById(R.id.mreq)

        init{
            itemView.setOnClickListener {
                clickListener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int):MyViewHolder {
        //Inflate the item view layout and create a new view holder
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.addjob_list,parent,false)
        return MyViewHolder(itemView, mListener)

    }

    override fun onBindViewHolder(holder:MyViewHolder, position: Int) {

        //bind the data to the view holder
        holder.Name.text = userList[position].Name
        holder.Mail.text = userList[position].Mail
        holder.Tele.text = userList[position].Tele


    }

    override fun getItemCount(): Int {

        //return the number of items in the data list
        return userList.size
    }

}
