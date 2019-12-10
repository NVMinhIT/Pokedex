package vnjp.monstarlaplifetime.pokedex.data.api

interface OperationCallback {
    fun onSuccess(obj: Any?)
    fun onError(obj: Any?)
}