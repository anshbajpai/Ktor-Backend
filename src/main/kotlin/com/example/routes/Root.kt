package com.example.routes

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.get

fun Route.root(){
    get("/"){
        call.respond(
            message = "Welcome to Boruto Heroes API",
            status = HttpStatusCode.OK

        )
    }
}