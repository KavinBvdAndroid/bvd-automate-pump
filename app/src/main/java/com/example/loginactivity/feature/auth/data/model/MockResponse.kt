package com.example.loginactivity.feature.auth.data.model

val mockLoginResponse = LoginResponse(
    accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9",
    refreshToken = "dGhpcy5pc1lvdXJSZWZyZXNoVG9rZW4",
    userId = 123456,
    phone = "+1234567890",
    lastName = "Doe",
    twofactorAccepted = 1,
    tokenType = "Bearer",
    expiresIn = 3600,
    firstName = "John",
    email = "john.doe@example.com",
    termAccepted = 1
)