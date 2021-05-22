package com.example.baicuoiky

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main2.viewPager2
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_term.*

class searchActivity : AppCompatActivity() {
    private lateinit var list:ArrayList<modelMain>
    private lateinit var auth: FirebaseAuth
    private lateinit var db: DatabaseReference
    private var count:Int = 0
    init{
        db = FirebaseDatabase.getInstance().reference
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        auth = FirebaseAuth.getInstance()
    }
    fun find(view: View){
        list = ArrayList()
        db.child(search.text.toString()).addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                for(i in snapshot.children){
                    count++
                }


                list.add(
                    modelMain(
                        snapshot.key.toString(),count,search.text.toString())
                )
                count = 0

                recycler_view1.adapter = AdapterSearch(list)
                recycler_view1.layoutManager = LinearLayoutManager(this@searchActivity)
                recycler_view1.setHasFixedSize(true)
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}