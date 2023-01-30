package com.buzzed.jpfinder.ui.screen

import com.buzzed.jpfinder.data.JP

data class JPUiState(
    val id: Int = 0,
    val lastName: String = "",
    val firstName: String = "",
    val middleName: String = "",
    val address1: String = "",
    val address2: String = "",
    val email: String = "",
    val fullName: String = "$lastName , $firstName $middleName",
    val actionEnabled: Boolean = false
)

fun JPUiState.toJP(): JP = JP(
    id = id,
    lastName = lastName,
    firstName = firstName,
    middleName = middleName,
    address1 = address1,
    address2 = address2,
    emailAddress = email
)

fun JP.toJPUiState(actionEnabled: Boolean = false): JPUiState = JPUiState(
    id = id,
    lastName = lastName,
    firstName = firstName,
    middleName = middleName,
    address1 = address1,
    address2 = address2,
    email = emailAddress,
)