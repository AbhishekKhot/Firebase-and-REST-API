package com.example.task.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.task.R
import com.example.task.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener { v ->
            val email = binding.etMail.text.toString().trim()
            val password = binding.editText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Snackbar.make(v, "Registered successfully", Snackbar.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_mainFragment_to_newsFragment)
                    } else {
                        Snackbar.make(v, it.exception?.message.toString(), Snackbar.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {

                Snackbar.make(v, "Empty fields not allowed", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}