package com.example.task.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task.R
import com.example.task.databinding.FragmentSignupBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class SignupFragment : Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient
    private val firebaseAuth = FirebaseAuth.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gso =
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(requireActivity(), gso)

        binding.tvForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_forgotPasswordFragment)
        }

        binding.ivGoogle.setOnClickListener {
            val signInIntent = gsc.signInIntent
            startActivityForResult(signInIntent, 1000)
        }



        binding.btnLogin.setOnClickListener { v ->

            val email: String = binding.etEmail.text.toString()
            val password: String = binding.etPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Snackbar.make(v, "Successfully Login", Snackbar.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_mainFragment_to_newsFragment)
                    } else {
                        Snackbar.make(v, it.exception?.message.toString(), Snackbar.LENGTH_SHORT)
                            .show()
                        Toast.makeText(requireActivity(),
                            it.exception?.message.toString(),
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                Snackbar.make(v, "Empty fields are not allowed", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode === 1000) {
            val task: Task<GoogleSignInAccount> =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                task.getResult(ApiException::class.java)
                findNavController().navigate(R.id.action_mainFragment_to_newsFragment)
            } catch (e: ApiException) {
                Toast.makeText(requireContext(),
                    "Something went wrong",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}