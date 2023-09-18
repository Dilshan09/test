package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ValueEventListener


class MainActivity444 : AppCompatActivity() {

// Using recyclerview going to show data array like cards

    private lateinit var recyclerview: RecyclerView
    private lateinit var retrive_jobArrayList: ArrayList<retrive_job>
    private lateinit var db: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main444)

        recyclerview = findViewById(R.id.retriveRecycle)
        recyclerview.layoutManager = LinearLayoutManager(this)

        retrive_jobArrayList = arrayListOf()

        db = FirebaseDatabase.getInstance().getReference("Add_Job")

        // Adding a ValueEventListener to retrieve data from the database
        db.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {
                    for (dataSnapShot in snapshot.children) {
                        // Retrieve each job data and add it to the retrive_jobArrayList
                        val user = dataSnapShot.getValue(retrive_job::class.java)
                        if (!retrive_jobArrayList.contains(user)) {
                            retrive_jobArrayList.add(user!!)
                        }

                    }

                    // Create an adapter with the retrieved data and set it to the RecyclerView
                    val mAdapter = addjob_Addapter(retrive_jobArrayList)
                    recyclerview.adapter = mAdapter
                    //recyclerview.adapter= addjob_Addapter(retrive_jobArrayList)

                    mAdapter.setOnItemClickListener(object : addjob_Addapter.onItemClickListener {
                        override fun onItemClick(position: Int) {

                            val intent = Intent(
                                this@MainActivity444,
                                MainActivity888::class.java
                            ) //from where to where

                            //put extras
                            intent.putExtra("jsId", retrive_jobArrayList[position].jsId)
                            intent.putExtra("Name", retrive_jobArrayList[position].Name)
                            intent.putExtra("Mail", retrive_jobArrayList[position].Mail)
                            intent.putExtra("Tele", retrive_jobArrayList[position].Tele)
                            intent.putExtra("fileName", retrive_jobArrayList[position].fileName)
                            startActivity(intent)
                            // to pass data from one activity to another activity

                            startActivity(intent)

                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {   //toast messege for db error
                Toast.makeText(this@MainActivity444, error.toString(), Toast.LENGTH_SHORT).show()
            }
        })

    }
}