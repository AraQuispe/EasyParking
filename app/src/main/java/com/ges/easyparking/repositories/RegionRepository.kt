package com.ges.easyparking.repositories

import com.ges.easyparking.clases.MyItem
import com.ges.easyparking.repositories.Result
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RegionRepository
@Inject
constructor(
    private val regionList: FirebaseFirestore
){

    fun getRegionList(): Flow<Result<List<MyItem>>> = flow{
        try{
            emit(Result.Loading<List<MyItem>>())
            val regionList = regionList.collection("regions").get().await().map{document->
                document.toObject(MyItem::class.java)
            }

            emit(Result.Success<List<MyItem>>(data = regionList))
        }catch (e: Exception){
            emit(Result.Error<List<MyItem>>(message = e.localizedMessage ?: "Error desconocido"))
        }
    }
}