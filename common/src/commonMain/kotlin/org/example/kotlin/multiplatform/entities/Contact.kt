package org.example.kotlin.multiplatform.entities

data class Contact(
  val gender: String,
  val name: Name,
  val location: Location,
  val email: String,
  val login: Login,
  val picture: Picture
)

data class Name(
  val first: String,
  val last: String
)

data class Location(
  val coordinates: Coordinates,
  val country: String,
  val city: String
)

data class Coordinates(
  val latitude: Double,
  val longitude: Double
)

data class Login(
  val username: String,
  val password: String
)

data class Picture(
  val large: String,
  val medium: String,
  val thumbnail: String
)