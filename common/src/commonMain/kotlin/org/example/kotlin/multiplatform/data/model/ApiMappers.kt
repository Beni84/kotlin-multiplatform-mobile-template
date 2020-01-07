package org.example.kotlin.multiplatform.data.model

import org.example.kotlin.multiplatform.entities.Contact
import org.example.kotlin.multiplatform.entities.Coordinates
import org.example.kotlin.multiplatform.entities.Location
import org.example.kotlin.multiplatform.entities.Login
import org.example.kotlin.multiplatform.entities.Name
import org.example.kotlin.multiplatform.entities.Picture

fun List<ApiContact>.toModel(): List<Contact> {
  return map { it.toModel() }
}

fun ApiContact.toModel(): Contact {
  return Contact(
    gender = gender,
    email = email,
    name = name.toModel(),
    location = location.toModel(),
    login = login.toModel(),
    picture = picture.toModel()
  )
}

fun ApiName.toModel(): Name {
  return Name(
    first = first,
    last = last
  )
}

fun ApiLocation.toModel(): Location {
  return Location(
    country = country,
    city = city,
    coordinates = coordinates.toModel()
  )
}

fun ApiCoordinates.toModel(): Coordinates {
  return Coordinates(
    latitude = latitude,
    longitude = longitude
  )
}

fun ApiLogin.toModel(): Login {
  return Login(
    username = username,
    password = password
  )
}

fun ApiPicture.toModel(): Picture {
  return Picture(
    large = large,
    medium = medium,
    thumbnail = thumbnail
  )
}