package com.mbieb.demojwt.controller

import org.springframework.web.bind.annotation.*

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello(): String {
        return "Helloooooo"
    }


}