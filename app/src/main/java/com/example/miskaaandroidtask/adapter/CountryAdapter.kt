package com.example.miskaaandroidtask.adapter

import android.content.Context
import android.graphics.drawable.PictureDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.miskaaandroidtask.ui.MainActivity
import com.example.miskaaandroidtask.R
import com.example.miskaaandroidtask.models.Country
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import kotlinx.android.synthetic.main.country_list_item.view.*


class CountryAdapter(
    var countries: List<Country>,
    var activity: MainActivity
): RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private lateinit var context: Context

    inner class CountryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val countryName: TextView = itemView.country_name
        val countryCapital: TextView = itemView.country_capital
        val textViewCapital: TextView = itemView.text_view_capital
        val countryFlag: ImageView = itemView.country_flag
        val countryPopulation: TextView = itemView.country_population
        val textViewPopulation: TextView = itemView.text_view_population
        val countrySubRegion: TextView = itemView.country_sub_region
        val textViewSubRegion: TextView = itemView.text_view_sub_region
        val countryRegion: TextView = itemView.country_region
        val textViewRegion: TextView = itemView.text_view_region
        val countryLanguages: TextView = itemView.country_languages
        val textViewLanguage: TextView = itemView.text_view_languages
        val countryBorder: TextView = itemView.country_border
        val textViewBorder: TextView = itemView.text_view_border
        val downArrow: ImageView = itemView.arrow_down
        val upArrow: ImageView = itemView.arrow_up
        val progressBar: ProgressBar = itemView.item_image_progress_bar
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.country_list_item,
            parent,
            false
        )
        context = parent.context
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.countryName.text = countries[position].name
        holder.countryCapital.text = countries[position].capital
        holder.countryRegion.text = countries[position].region
        holder.countrySubRegion.text = countries[position].subregion
        holder.countryLanguages.text = countries[position].languages[0].name
        holder.countryPopulation.text = countries[position].population.toString()
        if(!countries[position].borders.isEmpty()) {
            holder.countryBorder.text = countries[position].borders[0].toString()
        } else {
            holder.countryBorder.text = "NA"
        }

        val requestBuilder = GlideToVectorYou
            .init()
            .with(activity)
            .requestBuilder

        requestBuilder
            .load(countries[position].flag)
            .centerCrop()
            .listener(object : RequestListener<PictureDrawable> {
                override fun onResourceReady(
                    resource: PictureDrawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<PictureDrawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progressBar.visibility = View.GONE
                    return false
                }

                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<PictureDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.progressBar.visibility = View.VISIBLE
                    return false
                }

            })
            .into(holder.countryFlag)

        holder.upArrow.setOnClickListener(View.OnClickListener {
            holder.countryPopulation.visibility = View.GONE
            holder.countryBorder.visibility = View.GONE
            holder.countryCapital.visibility = View.GONE
            holder.countryLanguages.visibility = View.GONE
            holder.upArrow.visibility = View.GONE
            holder.countryRegion.visibility = View.GONE
            holder.countrySubRegion.visibility = View.GONE
            holder.textViewLanguage.visibility = View.GONE
            holder.textViewCapital.visibility = View.GONE
            holder.textViewBorder.visibility = View.GONE
            holder.textViewPopulation.visibility = View.GONE
            holder.textViewSubRegion.visibility = View.GONE
            holder.textViewRegion.visibility = View.GONE
            holder.downArrow.visibility = View.VISIBLE
        })

        holder.downArrow.setOnClickListener(View.OnClickListener {
            holder.countryPopulation.visibility = View.VISIBLE
            holder.countryBorder.visibility = View.VISIBLE
            holder.countryCapital.visibility = View.VISIBLE
            holder.countryLanguages.visibility = View.VISIBLE
            holder.upArrow.visibility = View.VISIBLE
            holder.countryRegion.visibility = View.VISIBLE
            holder.countrySubRegion.visibility = View.VISIBLE
            holder.textViewLanguage.visibility = View.VISIBLE
            holder.textViewCapital.visibility = View.VISIBLE
            holder.textViewBorder.visibility = View.VISIBLE
            holder.textViewPopulation.visibility = View.VISIBLE
            holder.textViewSubRegion.visibility = View.VISIBLE
            holder.textViewRegion.visibility = View.VISIBLE
            holder.downArrow.visibility = View.GONE
        })

    }

    override fun getItemCount(): Int {
        return countries.size
    }
}