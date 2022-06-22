package com.ges.easyparking.repositories

import com.ges.easyparking.clases.CarPark
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CarParkRepository
@Inject
constructor(
    private val carParkList: FirebaseFirestore
){

    fun getParkCarList(): Flow<Result<List<CarPark>>> = flow{
        try{
            emit(Result.Loading<List<CarPark>>())
            val carParkList = carParkList.collection("carparks").get().await().map{document->
                document.toObject(CarPark::class.java)
            }

            emit(Result.Success<List<CarPark>>(data = carParkList))
        }catch (e: Exception){
            emit(Result.Error<List<CarPark>>(message = e.localizedMessage ?: "Error desconocido"))
        }
    }
}