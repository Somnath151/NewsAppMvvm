package com.choudhary.newsappmvvm.ui.db

import androidx.room.TypeConverter
import com.choudhary.newsappmvvm.ui.models.Source

class Converters {
    @TypeConverter
    fun fromSource(source: Source) : String{

        return  source.name
    }

    @TypeConverter
    fun tosource(name: String) : Source{

        return Source(name, name)
    }
}