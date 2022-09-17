package com.choudhary.newsappmvvm.ui.Utils

sealed class Resource<T>(

    val data : T? = null,
    val messsage : String? = null
) {

    class Success<T>(data : T) : Resource<T>(data)
    class  Error<T>(messsage: String?, data: T?= null) : Resource<T>(data,messsage)
    class Loading<T> : Resource<T>()

}