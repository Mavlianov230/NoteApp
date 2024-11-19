package com.example.noteapp.ui.Activity.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentOnBoardPagerBinding
import com.example.noteapp.databinding.FragmentSignInBinding
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth


class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSignInBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var googleSingInClient: GoogleSignInClient
    private val signInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
         if (result.resultCode == Activity.RESULT_OK){
             val data: Intent? = result.data
             val task = GoogleSignIn.getSignedInAccountFromIntent(data)
             try {
                 val account = task.getResult(ApiException::class.java)
                 firebaseAuthWithGoogle(account?.idToken)
             }catch (e:ApiException){
                 updateUI(null)
             }
         }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(inflater, container, false)
        auth = Firebase.auth
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_sign_in_id))
            .requestEmail()
            .build()
        googleSingInClient = GoogleSignIn.getClient(requireContext(),gso)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnSignin.setOnClickListener {
            signInLauncher.launch(googleSingInClient.signInIntent)
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken,null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()){task ->
                if(task.isSuccessful){
                   val user = auth.currentUser
                    updateUI(user)
                }else{
                    updateUI(null)
                }
            }
    }
    private fun updateUI(user: FirebaseUser?) {
        if(user != null){
            findNavController().navigate(R.id.noteFragment)
        }else{
            Toast.makeText(requireContext(),"Аунтификация не прошла",Toast.LENGTH_SHORT).show()
        }
    }
}