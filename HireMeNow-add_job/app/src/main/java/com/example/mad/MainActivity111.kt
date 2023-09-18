package com.example.mad

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.OpenableColumns
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class  MainActivity111 : AppCompatActivity() {
                                                     //defining variables acording to input types
    private lateinit var ownerName :EditText
    private lateinit var ownerMail :EditText
    private lateinit var teleNo :EditText
//    private lateinit var slot1 :Button
//    private lateinit var slot2 :Button
//    private lateinit var slot3 :Button
//    private lateinit var slot4 :Button
//    private lateinit var slot5 :Button
//    private lateinit var slot6 :Button
//    private lateinit var slot7 :Button
//    private lateinit var slot8 :Button
    private lateinit var cancel1 :Button
    private lateinit var submit1 :Button  //for vector assets


    private lateinit var dbRef: DatabaseReference  //database reference

    val PDF : Int = 0
    lateinit var uri : Uri
    lateinit var mStorage : StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main111)

        ownerName = findViewById(R.id.Name)     //mentioning the variables for perticular id's
        ownerMail = findViewById(R.id.Mail)
        teleNo = findViewById(R.id.TeleNo)
//        slot1 = findViewById(R.id.bt1)
//        slot2 = findViewById(R.id.bt2)
//        slot3 = findViewById(R.id.bt3)
//        slot4 = findViewById(R.id.bt4)
//        slot5 = findViewById(R.id.bt5)
//        slot6 = findViewById(R.id.bt6)
//        slot7 = findViewById(R.id.bt7)
//        slot8 = findViewById(R.id.bt8)
        cancel1 = findViewById(R.id.cancel2)
        submit1 = findViewById(R.id.submit2)

        dbRef = FirebaseDatabase.getInstance().getReference("Add_Slot") //database mentioning

        submit1.setOnClickListener {
            saveAddJob(uri)                     // save the data according to on click listner
        }

        val pdfBtn = findViewById<View>(R.id.upload) as Button

        submit1.setOnClickListener {
            val intent = Intent(this, MainActivity333::class.java) // where to goes after submitting
            startActivity(intent)}


        cancel1.setOnClickListener { //page clearing on click function
//            clearFields()
            Toast.makeText(this, "Page cleared.", Toast.LENGTH_SHORT).show()
        }

        mStorage = FirebaseStorage.getInstance().getReference("Agreements_Uploads")

        pdfBtn.setOnClickListener(View.OnClickListener {
                view: View? -> val intent = Intent()
            intent.type = "application/pdf"
            intent.setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent,"Select PDF"), PDF)
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == PDF) {
            data?.data?.let { uri ->
                val fileName = getFileName(uri)
                findViewById<TextView>(R.id.upload).text = fileName

                submit1.setOnClickListener {
                    saveAddJob(uri)
                }
            }
        }
    }

    private fun getFileName(uri: Uri): String {
        var result = ""

        if (uri.scheme == "content") {
            contentResolver.query(uri, null, null, null, null)?.use {
                if (it.moveToFirst()) {
                    val columnIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    if (columnIndex != -1) {
                        result = it.getString(columnIndex)
                    }
                }
            }
        }

        if (result.isEmpty()) {
            result = uri.lastPathSegment ?: ""
        }

        return result
    }

//    private fun clearFields() {  //page clearing
//        ownerName.text.clear()
//        ownerMail.text.clear()
//        teleNo.text.clear()
//    }
    private fun saveAddJob(uri: Uri) {
        //getting values
        val fileName = getFileName(uri)
        val reference = mStorage.child(fileName)

        reference.putFile(uri).addOnSuccessListener {
            Toast.makeText(this, "Data saved successfully!", Toast.LENGTH_SHORT).show()

        val jsId = dbRef.push().key!!

        val Name = ownerName.text.toString()
        val Mail= ownerMail.text.toString()
        val Tele = teleNo.text.toString()


        if(Name.isEmpty()){                           //if fields not filled
            ownerName.error = " Please enter Name"
        }
        if(Mail.isEmpty()){
            ownerMail.error = " Please enter Mail"
        }
        if(Tele.isEmpty()){
            teleNo.error = " Please enter Telephone Number"
        }


        val addJob = addJobModel(jsId, Name, Mail, Tele) // crete object

            addJob.fileName = fileName
            dbRef.child(jsId).setValue(addJob)

//            .addOnCompleteListener {
//                Toast.makeText(this, "Data inserted successfully. ", Toast.LENGTH_LONG).show()

                ownerName.text.clear()
                ownerMail.text.clear()
                teleNo.text.clear()
                findViewById<TextView>(R.id.upload).text = ""   // clear the form when a data record passed


//            }
        }.addOnFailureListener{err ->
                Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show() //toast error messege
            }
    }
}
