package com.finalproject.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.finalproject.model.Reservation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase

class ReservationDAO {
    private var userId: String
    private var firestore: FirebaseFirestore

    init {
        val user = Firebase.auth.currentUser?.uid
        userId = "${user}"
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun getAllData(): MutableLiveData<List<Reservation>> {
        val listReservation = MutableLiveData<List<Reservation>>()

        firestore.collection("reservations")
            .document(userId)
            .collection("myReservations")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val list = ArrayList<Reservation>()
                    val reservations = snapshot.documents
                    reservations.forEach {
                        val reservation = it.toObject(Reservation::class.java)
                        if (reservation != null) {
                            list.add(reservation)
                        }
                    }

                    listReservation.value = list
                }
            }
        return listReservation
    }

    fun saveReservation(reservation: Reservation){
        val document: DocumentReference

        if(reservation.id.isEmpty()) {
            document =  firestore.collection("reservations")
                .document(userId)
                .collection("myReservations")
                .document()
            reservation.id = document.id
        } else {
            document = firestore.collection("reservations")
                .document(userId)
                .collection("myReservations")
                .document(reservation.id)
        }
        val set = document.set(reservation)
        set.addOnSuccessListener {
            Log.d("AddReservation", "Reservation added")
        }
            .addOnCanceledListener {
                Log.d("AddReservation", "Failed to add reservation")
            }
    }


    fun deleteReservation(reservation: Reservation) {
        if (reservation.id.isNotEmpty()) {
            firestore.collection("reservations")
                .document(userId)
                .collection("myReservations")
                .document(reservation.id)
                .delete()
        }
    }
}