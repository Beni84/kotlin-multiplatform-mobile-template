package org.example.kotlin.multiplatform.app

import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.gigigo.baserecycleradapter.viewholder.BaseViewHolder
import org.example.kotlin.multiplatform.entities.Contact

class ContactViewHolder(
  context: Context,
  parent: ViewGroup
) : BaseViewHolder<Contact>(
  context,
  parent,
  R.layout.view_holder_contact
) {

  private var contactImage = itemView.findViewById<ImageView>(R.id.contact_image)
  private var contactName = itemView.findViewById<TextView>(R.id.contact_name)

  override fun bindTo(value: Contact, position: Int) {
    Glide.with(itemView).load(value.picture.medium).into(contactImage)
    contactName.text = "${value.name.first} ${value.name.last}"
  }

}
