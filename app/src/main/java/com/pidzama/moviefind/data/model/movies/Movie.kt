package com.pidzama.moviefind.data.model.movies

import androidx.room.*
import java.io.Serializable

@Entity(tableName = "movies")
data class Movie(
    @Embedded(prefix = "links")
    val _links: Links,
    @ColumnInfo(name = "averageRuntime")
    val averageRuntime: Int,
    @Embedded(prefix = "dvdCountry")
    val dvdCountry: Any?,
    @ColumnInfo(name = "ended")
    val ended: String,
    @Embedded(prefix = "externals")
    val externals: Externals,
    @ColumnInfo(name = "genres")
    @TypeConverters
    val genres: List<String>,
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @Embedded(prefix = "image")
    val image: Image?,
    @ColumnInfo(name = "language")
    val language: String,
    @ColumnInfo(name = "name")
    val name: String,
    @Embedded(prefix = "network")
    val network: Network,
    @ColumnInfo(name = "officialSite")
    val officialSite: String,
    @ColumnInfo(name = "premiered")
    val premiered: String,
    @Embedded(prefix = "rating")
    val rating: Rating,
    @ColumnInfo(name = "runtime")
    val runtime: Int,
    @Embedded(prefix = "schedule")
    val schedule: Schedule,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "summary")
    val summary: String,
    @ColumnInfo(name = "type")
    val type: String,
    @ColumnInfo(name = "updated")
    val updated: Int,
    @ColumnInfo(name = "url")
    val url: String,
    @Embedded(prefix = "webChannel")
    val webChannel: Any?,
    @ColumnInfo(name = "weight")
    val weight: Int,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Serializable

data class Country(
    @ColumnInfo(name = "code")
    val code: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "timezone")
    val timezone: String
)

data class Externals(
    @ColumnInfo(name ="imdb")
    val imdb: String,
    @ColumnInfo(name ="thetvdb")
    val thetvdb: Int,
    @ColumnInfo(name ="tvrage")
    val tvrage: Int
)

data class Image(
    @ColumnInfo(name = "medium")
    val medium: String?,
    @ColumnInfo(name = "original")
    val original: String?
)

@Entity
data class Links(
    @Embedded(prefix = "previousepisode")
    val previousepisode: Previousepisode,
    @Embedded(prefix = "self")
    val self: Self
)

data class Network(
    @Embedded(prefix = "country")
    val country: Country,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "officialSite")
    val officialSite: String
)

data class Previousepisode(
    @ColumnInfo(name = "href")
    val href: String
)

data class Rating(
    @ColumnInfo(name = "average")
    val average: Double
)

data class Schedule(
    @ColumnInfo(name = "days")
    @TypeConverters
    val days: List<String>,
    @ColumnInfo(name = "time")
    val time: String
)

data class Self(
    @ColumnInfo(name = "href")
    val href: String
)