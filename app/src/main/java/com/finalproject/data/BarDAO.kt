package com.finalproject.data

import androidx.lifecycle.MutableLiveData
import com.finalproject.model.Bar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase

class BarDAO {
    private var userId: String
    private var firestore: FirebaseFirestore

    init {
        val user = Firebase.auth.currentUser?.uid
        userId = "${user}"
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun getAllData(): MutableLiveData<List<Bar>> {
        val listBar = MutableLiveData<List<Bar>>()

        firestore.collection("bares")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val list = ArrayList<Bar>()
                    val bares = snapshot.documents
                    bares.forEach {
                        val bar = it.toObject(Bar::class.java)
                        if (bar != null) {
                            list.add(bar)
                        }
                    }

                    listBar.value = list
                }
            }
        return listBar
    }

    fun saveBar(bar: Bar){
        val document: DocumentReference

        if(bar.id.isEmpty()) {
            document =  firestore.collection("bares")
                .document()
            bar.id = document.id
            val set = document.set(bar)
        }
    }
}