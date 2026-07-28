package com.automation.karate.runners;

import com.intuit.karate.junit5.Karate;

class AuthRunner {

    @Karate.Test
    Karate testAuth() {
        return Karate.run("classpath:features/api/auth.feature");
    }
}
