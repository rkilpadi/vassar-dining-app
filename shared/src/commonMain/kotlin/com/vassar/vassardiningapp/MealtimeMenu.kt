package com.vassar.vassardiningapp

import kotlinx.serialization.Serializable

@Serializable
data class MealtimeMenu (
    val cafe: String,
    val date: String,
    val label: String,
    val menuItems: List<MenuItem2>
){

}