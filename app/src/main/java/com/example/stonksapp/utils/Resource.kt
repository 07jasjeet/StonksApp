package com.example.stonksapp.utils

/**Use this class to pass [data] and [status] to view-model.
 * @param error Whenever an error is occurred, the error must be passed to view-model through this parameter. *null* means no error.*/
class Resource<T>(val status: Status, val data: T?, val error: ResponseError? = null) {
    
    enum class Status {
        LOADING, FAILED, SUCCESS;
        
        val isSuccessful get() = this == SUCCESS
        val isFailed get() = this == FAILED
        val isLoading get() = this == LOADING
    }
    
    val isSuccessful get() = status.isSuccessful
    val isFailed get() = status.isFailed
    val isLoading get() = status.isLoading
    
    inline fun ifFailed(ret: (Resource<T>) -> Unit): Resource<T> {
        if (status == Status.FAILED){
            ret(this)
        }
        return this
    }
    
    inline fun ifSuccessful(ret: (T) -> Unit): Resource<T> {
        if (status == Status.SUCCESS){
            ret(this.data!!)
        }
        return this
    }

    override fun toString(): String {
        return "Resource(status=${status.name}, data=${data.toString()}, error=${error.toString()})"
    }
    
    companion object {
        fun <S> success(data: S): Resource<S> =
            Resource(Status.SUCCESS, data)
        
        /** Return [ResponseError] if any.*/
        fun <S> failure(error: ResponseError? = null, data: S? = null): Resource<S> =
            Resource(Status.FAILED, data, error)
        
        fun <S> loading(data: S? = null): Resource<S> =
            Resource(Status.LOADING, data)
    }
}