package com.example.task.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.task.R
import com.example.task.databinding.FragmentForgotPasswordBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordFragment : Fragment() {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnResetPassword.setOnClickListener {v->
            val email: String = binding.etMail.text.toString().trim()
            if (email.isNotEmpty()) {
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Snackbar.make(v,
                            "Password link has been shared with you via email",
                            Snackbar.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_forgotPasswordFragment_to_mainFragment)
                    }
                }
            } else {
                Snackbar.make(v, "Please enter valid email address", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}