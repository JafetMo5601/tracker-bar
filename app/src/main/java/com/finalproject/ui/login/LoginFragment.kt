package com.finalproject.ui.login

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import com.finalproject.R

class LoginFragment: Fragment() {

    private fun loginError() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setNegativeButton(getString(R.string.ok)) { _, _ ->}
        //builder.setTitle(R.string.login_failed)
        builder.setMessage(getString(R.string.login_failed))
        builder.show()
    }
}