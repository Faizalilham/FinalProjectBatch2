package com.example.finalprojectbatch2.model

import java.time.LocalDate
import java.util.*

data class Model (
    var id : Int,
    var name : String,
    var username : String,
    var email : String,
    var password : String,
    var updatedAt : Date,
    var createdAt : Date,
    var token : String,

)
