package com.example.abstraction

// using abstract class we can skip interface forcefully implementations

abstract class AuthForget : Auth {

    override fun forgetPassword() {
        TODO("Not yet implemented")
    }


}

abstract class AuthLogin : Auth {

    override fun login() {
        TODO("Not yet implemented")
    }


}