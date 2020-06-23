package com.zisa.guess

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_material.*
import kotlinx.android.synthetic.main.content_material.*

class MaterialActivity : AppCompatActivity() {

    val secretNumber = SecretNumber()
    val TAG = MaterialActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_material)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            AlertDialog.Builder(this)
                .setTitle("Replay me")
                .setMessage("Are you sure?")
                .setPositiveButton(getString(R.string.OK), {dialog, which ->
                    secretNumber.reset()
                    counter.setText(secretNumber.count.toString())
                    ed_number.setText("")
                })
                .setNeutralButton("Cancel", null)
                .show()

        }
        counter.setText(secretNumber.count.toString())
        Log.d(TAG, "onCreate: "+secretNumber.secrent)
        val count = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getInt("REC_COUNTER", -1)

        var nick = getSharedPreferences("guess", Context.MODE_PRIVATE)
            .getString("REC_NICKNAME", null)

        Log.d(TAG, "data: $count/$nick")
    }

    fun check(view : View) {
        val n = ed_number.text.toString().toInt()
        println("number : $n")
        Log.d(TAG, "number:" + n)
        val diff = secretNumber.validate(n)
        var message = getString(R.string.Yes_you_got_it)
        if(diff<0) {
            message = getString(R.string.Bigger)
        }else if (diff > 0){
            message = getString(R.string.Smaller)
        }
        counter.setText(secretNumber.count.toString())
//        Toast.makeText(this,message,Toast.LENGTH_LONG).show()

        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dialog_title))
            .setMessage(message)
            .setPositiveButton(getString(R.string.OK), {dialog, which ->
                if(diff == 0)
                {
                    val intent = Intent(this, RecordActivity::class.java)
                    intent.putExtra("COUNTER", secretNumber.count)
                    startActivity(intent)
                }
            })
            .show()
    }

}
