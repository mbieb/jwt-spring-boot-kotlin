package com.mbieb.demojwt.controller

import com.mbieb.demojwt.model.AuthenticationRequest
import com.mbieb.demojwt.model.AuthenticationResponse
import com.mbieb.demojwt.service.MyUserDetailsService
import com.mbieb.demojwt.util.JwtUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthenticationController {
    @Autowired
    private val authenticationManager: AuthenticationManager? = null

    @Autowired
    private val userDetailsService: MyUserDetailsService? = null

    @Autowired
    private val jwtTokenUtil: JwtUtil? = null

    @PostMapping("/token")
    fun createAuthenticationToken(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<*> {
        try {
            authenticationManager!!.authenticate(
                    UsernamePasswordAuthenticationToken(authenticationRequest.username, authenticationRequest.password)
            )
        } catch (e: BadCredentialsException) {
            throw Exception("incorrect username or password!", e)
        }

        val userDetails = userDetailsService!!.loadUserByUsername(authenticationRequest.password)
        val jwt = jwtTokenUtil!!.generateToken(userDetails)

        return ResponseEntity.ok<Any>(AuthenticationResponse(jwt))
    }
}