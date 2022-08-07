package com.ges.easyparking.repositories

import com.ges.easyparking.clases.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepository
@Inject
constructor(
    private val userList: FirebaseFirestore
){
    fun addNewUser(user: User) {
        try {
            userList.collection("users").document(user.id).set(user)
        } catch (e: Exception){
            e.printStackTrace()
        }
    }
    fun getUserList(): Flow<Result<List<User>>> = flow{
        try{
            emit(Result.Loading<List<User>>())
            val userList = userList.collection("users").get().await().map{document->
                document.toObject(User::class.java)
            }

            emit(Result.Success<List<User>>(data = userList))
        }catch (e: Exception){
            emit(Result.Error<List<User>>(message = e.localizedMessage ?: "Error desconocido"))
        }
    }
}