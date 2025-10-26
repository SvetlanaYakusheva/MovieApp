package com.practicum.movieappwithmvp.ui.names

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicum.movieappwithmvp.domain.models.Person

class PersonsAdapter () : RecyclerView.Adapter<PersonViewHolder>() {

    var persons = ArrayList<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder = PersonViewHolder(parent)

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(persons.get(position))

    }

    override fun getItemCount(): Int = persons.size


}