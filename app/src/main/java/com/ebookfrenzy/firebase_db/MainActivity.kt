package com.ebookfrenzy.firebase_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    private var users = ArrayList<Thingstodo>()
    var listview: ListView? = null
    var addButton: Button? = null
    var getValue: EditText? = null
    val changeListener: ValueEventListener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            if (snapshot.hasChildren()) {
                // great we have kids
                var count = snapshot.childrenCount
                users.clear()
                for (child in snapshot.children){
                    val holdData = child.getValue(Thingstodo::class.java)
                    users.add(holdData!!)
                    Log.i("todo", child.getValue().toString())
                }
            }
        }

        override fun onCancelled(error: DatabaseError) {
            TODO("Not yet implemented")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listview = findViewById(R.id.listView1)
        addButton = findViewById(R.id.button1)
        getValue = findViewById(R.id.editText1)
// By ID we can use each component
// which id is assign in xml file
// use findViewById() to get the
// CalendarView and TextView

            with(listview) {
                this?.setAdapter(adapter)
            }
            with(addButton) {
                with(listview) {
                    this?.setAdapter(adapter)
                }
//                this?.setOnClickListener(android.view.View.OnClickListener {
//                    if (getValue2 != null) {
//                        with(ListElementsArrayList) { add(getValue2.getText().toString()) }
//                    }
//                    adapter.notifyDataSetChanged()
//                })
            }

//        database = Firebase.database.reference

//        database.child("user").setValue("Dave")
//        database.child("address").setValue("123 Main Street")
//        var currUser = Person("Dave", "Boesen", "123 Main Street", "home@address.com", "00001")
//        database.child("users").child(currUser.ID).setValue(currUser)
//        var currUser2 = Person("Jeff", "Herman", "543 Main Street", "home2@address.com", "00002")
//        database.child("users").child(currUser2.ID).setValue(currUser2)
//        var currUser3 = Person("Bali", "Matesi", "456 Main Street", "home3@address.com", "00003")
//        database.child("users").child(currUser3.ID).setValue(currUser3)
//
//        database = Firebase.database.getReference("/users")
//
//        database.addValueEventListener(changeListener)



    }

    override fun onDestroy() {
        super.onDestroy()
        database.removeEventListener(changeListener)

    }
}
