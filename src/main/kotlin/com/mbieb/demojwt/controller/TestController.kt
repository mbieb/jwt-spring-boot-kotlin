package com.mbieb.demojwt.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("test")
    fun test() = arrayOf(
            arrayOf("Bandung", "Jakarta"),
            arrayOf("Jakarta", "Bandung")
    )
}