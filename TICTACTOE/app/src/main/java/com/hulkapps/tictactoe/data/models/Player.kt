package com.hulkapps.tictactoe.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Player(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo var name: String,
    @ColumnInfo var score: Int,
    @ColumnInfo var scoreVsAi: Int,
    @ColumnInfo var theme: String,
    @ColumnInfo var turn: Boolean
)