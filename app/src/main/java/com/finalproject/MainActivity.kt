package com.finalproject

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.finalproject.databinding.ActivityMainBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    companion object{
        private const val GOOGLE_SIGN_IN = 9001
    }

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        binding.btLogin.setOnClickListener { login(); }

        binding.forgetPassword.setOnClickListener { resetPassword(); }

        binding.btSignup.setOnClickListener { signUp(); }

        binding.ibFacebook.setOnClickListener { facebookAuth() }


        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_clietn_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)
        binding.ibGoogle.setOnClickListener { googleAuth(); }
    }

    private fun facebookAuth() {

    }

    private fun googleAuth() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN)
    }

    private fun firebaseAuthWithGoogle(idToken: String){
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if(task.isSuccessful){
                    val user = auth.currentUser
                    update(user)
                }else{
                    update(null)
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
                Toast.makeText(this, "Sign in succesfully", Toast.LENGTH_LONG).show()
            }catch (e: ApiException){
                Log.w(TAG, "Google sign in failed")
            }
        }
    }

    private fun signUp() {
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }

    private fun resetPassword() {
        val intent = Intent(this, ResetPassword::class.java)
        startActivity(intent)
    }

    private fun login() {
        val email = binding.emailTv.text.toString()
        val password = binding.tvPassword.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                    task->
                if (task.isSuccessful) {
                    Log.d("Sign in user", "Authenticated")
                    val user = auth.currentUser
                    update(user)
                } else {
                    Toast.makeText(baseContext, "Failed, email or password invalid", Toast.LENGTH_LONG).show()
                     update(null)
                }
            }
    }

    private fun update(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }

    public override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        update(user)
    }
}