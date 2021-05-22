package com.example.baicuoiky

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main2.*

class MainActivity2 : AppCompatActivity() {
    private lateinit var db: DatabaseReference
    private lateinit var auth: FirebaseAuth
    private lateinit var list:ArrayList<modelMain>
    private lateinit var Adapter:AdapterMain
    private var count:Int = 0
    init{
        db = FirebaseDatabase.getInstance().reference
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        auth = FirebaseAuth.getInstance()
        getCard()
    }

    private fun getCard(){
        list = ArrayList()
        db.child(auth.uid.toString()).addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                for(i in snapshot.children){
                    count++
                }


                list.add(
                    modelMain(
                        snapshot.key.toString(),count,"lecongtu")
                )
                count = 0

                Adapter = AdapterMain(this@MainActivity2,list)
                viewPager2.adapter = Adapter
                viewPager2.setPadding(10,0,100,0)
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
    fun search(view:View){
        val i:Intent = Intent(this,searchActivity::class.java)
        startActivity(i)
    }
}