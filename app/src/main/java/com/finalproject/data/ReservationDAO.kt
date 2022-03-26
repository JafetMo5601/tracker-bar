package com.finalproject.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.finalproject.model.Reservation

@Dao
interface ReservationDAO {
    @Query("SELECT * FROM Reservation")
    fun getAllData(): LiveData<List<Reservation>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addReservation(reservation: Reservation)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateReservation(reservation: Reservation)

    @Delete
    suspend fun deleteReservation(reservation: Reservation)
}