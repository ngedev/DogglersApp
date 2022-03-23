/*
* Copyright (C) 2021 The Android Open Source Project.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.example.dogglers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglers.R
import com.example.dogglers.const.Layout

import com.example.dogglers.data.DataSource

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // : Initialize the data using the List found in data/DataSource
    val mDogs = DataSource.dogs

    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        // : Declare and initialize all of the list item UI components
        val ivDog = view?.findViewById<ImageView>(R.id.ivDog)
        val tvName = view?.findViewById<TextView>(R.id.tvName)
        val tvAge = view?.findViewById<TextView>(R.id.tvAge)
        val tvHobby = view?.findViewById<TextView>(R.id.tvHobby)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        // : Use a conditional to determine the layout type and set it accordingly.
        //  if the layout variable is Layout.GRID the grid list item should be used. Otherwise the
        //  the vertical/horizontal list item should be used.
        var layoutItem = R.layout.vertical_horizontal_list_item
        if(layout==Layout.GRID){
            layoutItem = R.layout.grid_list_item
        }

        //  Inflate the layout
        val viewInflated = LayoutInflater.from(context).inflate(layoutItem, parent, false)

        // : Null should not be passed into the view holder. This should be updated to reflect
        //  the inflated layout.
        return DogCardViewHolder(viewInflated)
    }

    override fun getItemCount(): Int = mDogs.size // : return the size of the data set instead of 0

    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        // : Get the data at the current position
        val dog = mDogs[position]

        // : Set the image resource for the current dog
        holder.ivDog?.setImageResource(dog.imageResourceId)

        // : Set the text for the current dog's name
        holder.tvName?.text = dog.name

        val resources = context?.resources
        // : Set the text for the current dog's hobbies by passing the hobbies to the
        //  R.string.dog_hobbies string constant.
        //  Passing an argument to the string resource looks like:
        //  resources?.getString(R.string.dog_hobbies, dog.hobbies)
        holder.tvHobby?.text = resources?.getString(R.string.dog_hobbies, dog.hobbies)

        // : Set the text for the current dog's age
        holder.tvAge?.text = resources?.getString(R.string.dog_age, dog.age)
    }
}
