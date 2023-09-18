package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.FirebaseDatabase

class MainActivity888 : AppCompatActivity() {

    private lateinit var aajobId :TextView   //declaring views according to the input views
    private lateinit var aName :TextView
    private lateinit var aMail :TextView
    private lateinit var aTeleNo :TextView
    private lateinit var afile :TextView
    private lateinit var edit5 :Button
    private lateinit var delete5 :Button


    override fun onCreate(savedInstanceState: Bundle?) {  //when activity created
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main888)

        initView() // initialising views
        setValuesToViews() // setting values to views

        edit5.setOnClickListener {  //for submit button operations
            openUpdateDialog(
                intent.getStringExtra("jsId").toString(),
                intent.getStringExtra("Name").toString()

            )
        }

        delete5.setOnClickListener { //for delete button operations
            deleteRecord(
                intent.getStringExtra("jsId").toString()
            )
        }

    }

    private fun deleteRecord(
        id:String
    ){
        val dbRef = FirebaseDatabase.getInstance().getReference("Add_Job").child(id)  // db name and according to its id
        val mTask = dbRef.removeValue()

        mTask.addOnSuccessListener {
            Toast.makeText(this,"This agreement data deleted",Toast.LENGTH_LONG).show()//toast messeges for deleting

            val intent = Intent(this, MainActivity444 ::class.java) //when delete where to goes
            finish()
            startActivity(intent)
        }.addOnFailureListener{error ->

            Toast.makeText(this,"Deleting Err ${error.message}",Toast.LENGTH_LONG).show()
            //display error messege
        }
    }

    private fun initView(){
        aajobId = findViewById(R.id.aajobId)                // find id
        aName = findViewById(R.id.aName)
        aMail = findViewById(R.id.aMail)
        aTeleNo = findViewById(R.id.aTeleNo)
        afile = findViewById(R.id.afile)
        edit5 = findViewById(R.id.edit5)
        delete5 = findViewById(R.id.delete5)

    }


    private fun setValuesToViews(){
        aajobId.text = intent.getStringExtra("jsId")             // set values
        aName.text = intent.getStringExtra("Name")
        aMail.text = intent.getStringExtra("Mail")
        aTeleNo.text = intent.getStringExtra("Tele")
        afile.text = intent.getStringExtra("fileName")

    }


    private fun openUpdateDialog(
        jsId: String, //id get as it is
        Name: String
        
    ) {
        // Create an AlertDialog and inflate the custom layout

        val mDialog = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val mDialogView = inflater.inflate(R.layout.update_addjob, null)

        mDialog.setView(mDialogView)

        // Find the EditText fields in the dialog layout


//        val cJobid = mDialogView.findViewById<TextView>(R.id.caajobid)
        val caaName = mDialogView.findViewById<EditText>(R.id.caaName)
        val caaMail = mDialogView.findViewById<EditText>(R.id.caaMail)
        val caaTeleNo = mDialogView.findViewById<EditText>(R.id.caaTeleNo)
        val caafile = mDialogView.findViewById<EditText>(R.id.caafile)

//        val ctotal = mDialogView.findViewById<EditText>(R.id.caatott)

        val btnConfirm = mDialogView.findViewById<Button>(R.id.cedit5)

        // Set the initial values of the EditText fields with the received intent extras


//        cJobid.setText(intent.getStringExtra("jobId").toString())

        caaName.setText(intent.getStringExtra("Name").toString())
        caaMail.setText(intent.getStringExtra("Mail").toString())
        caaTeleNo.setText(intent.getStringExtra("Tele").toString())
        caafile.setText(intent.getStringExtra("fileName").toString())

//        ctotal.setText(intent.getStringExtra("tot").toString())

        mDialog.setTitle("Updating $Name Record")

        val alertDialog = mDialog.create()
        alertDialog.show()

        btnConfirm.setOnClickListener {

            //call the updateJobData function with updated values
            updateJobData(
                jsId,
                caaName.text.toString(),
                caaMail.text.toString(),
                caaTeleNo.text.toString(),
                caafile.text.toString()

            )
            Toast.makeText(applicationContext, " The Job Data Updated.",Toast.LENGTH_LONG).show()

            aName.text = caaName.text.toString()
            aMail.text = caaMail.text.toString()
            aTeleNo.text = caaTeleNo.text.toString()
            afile.text = caafile.text.toString()

            alertDialog.dismiss()
        }

    }

    private fun updateJobData(
        id: String,
        Name: String,
        Mail: String,
        TelephoneNo: String,
        file: String
    ){
        //det db reference and update specific data record
        val dbRef = FirebaseDatabase.getInstance().getReference("Add_Job").child(id)
        val jobInfo = addJobModel(id,Name,Mail,TelephoneNo,file)
        dbRef.setValue(jobInfo)
    }


}