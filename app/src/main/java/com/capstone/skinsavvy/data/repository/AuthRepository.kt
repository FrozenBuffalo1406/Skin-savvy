package com.capstone.skinsavvy.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

class AuthRepository (
    private val firebaseAuth: FirebaseAuth
) {
    suspend fun signin(email: String, password: String): FirebaseUser? {
        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            result.user
        } catch (e: Exception) {
            null
        }
    }

    suspend fun signup(email: String, password: String, displayName: String): FirebaseUser? {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            // Update user profile with display name
            result.user?.let { user ->
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(displayName)
                    .build()
                user.updateProfile(profileUpdates).await()
            }
            result.user
        } catch (e: FirebaseAuthUserCollisionException) {
            null
        } catch (e: Exception) {
            null
        }
    }

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser

    fun logout() {
        firebaseAuth.signOut()
    }
}