package org.example.kotlin.multiplatform.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResult(
  val results: List<ApiContact>
)

@Serializable
data class ApiContact(
  val gender: String,
  val name: ApiName,
  val location: ApiLocation,
  val email: String,
  val login: ApiLogin,
  val picture: ApiPicture
)

@Serializable
data class ApiName(
  val first: String,
  val last: String
)

@Serializable
data class ApiLocation(
  val coordinates: ApiCoordinates,
  val country: String,
  val city: String
)

@Serializable
data class ApiCoordinates(
  val latitude: Double,
  val longitude: Double
)

@Serializable
data class ApiLogin(
  val username: String,
  val password: String
)

@Serializable
data class ApiPicture(
  val large: String,
  val medium: String,
  val thumbnail: String
)