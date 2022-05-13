package org.wit.placemark.activities

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import org.wit.placemark.databinding.ActivityAuthBinding
import org.wit.placemark.main.ProviderType


class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //this is our binding of the app.
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //this is our logic of the buttons
        //first one will be for register.
        binding.signUpButton.setOnClickListener() {
            if (binding.emailEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty()) {

                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(binding.emailEditText.text.toString(),
                        binding.passwordEditText.text.toString()).addOnCompleteListener(this) { task ->
                        if (task.isSuccessful){
                            startPlaceMarkActivityList()
                            //overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
                            //finish()


                        } else {
                            showAlert()
                        }

                    }

            }

        }

        binding.loginButton.setOnClickListener() {
            if (binding.emailEditText.text.isNotEmpty() && binding.passwordEditText.text.isNotEmpty()) {

                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(binding.emailEditText.text.toString(),
                        binding.passwordEditText.text.toString()).addOnCompleteListener(this) { task ->
                        if (task.isSuccessful){
                            startPlaceMarkActivityList()
                            //overridePendingTransition(R.anim.fade_in,R.anim.fade_out)
                            //finish()


                        } else {
                            showAlert()
                        }

                    }
            }

        }
    }


    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("There was an error authenticating user")
        builder.setPositiveButton("Accept", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun startPlaceMarkActivityList(){
        val i = Intent(this, PlacemarkListActivity::class.java)
        startActivity(i)
    }
}