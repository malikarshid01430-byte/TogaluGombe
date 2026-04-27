package com.example.togalugombe.data

import androidx.room.*

@Dao
interface PuppetDao {

    @Query("SELECT * FROM puppets")
    fun getAll(): List<Puppet>

    @Query("SELECT * FROM puppets WHERE isSaved = 1")
    fun getSaved(): List<Puppet>

    @Update
    fun update(puppet: Puppet)

    @Insert
    fun insertAll(vararg puppet: Puppet)
}