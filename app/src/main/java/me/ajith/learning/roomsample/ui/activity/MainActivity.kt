package me.ajith.learning.roomsample.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import me.ajith.learning.roomsample.ui.fragment.MainFragment
import me.ajith.learning.roomsample.R
import me.ajith.learning.roomsample.common.DatabaseBuilder
import me.ajith.learning.roomsample.common.ViewModelFactory
import me.ajith.learning.roomsample.data.User
import me.ajith.learning.roomsample.data.local.DatabaseHelperImpl
import me.ajith.learning.roomsample.viewmodel.MainViewModel
import java.util.Locale

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edUserName:EditText
    private lateinit var edUserNickName:EditText
    private lateinit var saveButton:Button
    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setMainFragment()
        setupView()
        setupViewModel()
    }

    private fun setupView(){
        edUserName = findViewById(R.id.ed_user_name)
        edUserNickName = findViewById(R.id.ed_user_nickname)
        saveButton = findViewById(R.id.save_button)
        saveButton.setOnClickListener(this)
    }

    private fun setMainFragment(){
        if(supportFragmentManager.findFragmentByTag(MainFragment.TAG)==null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, MainFragment.newInstance())
                .commit()
        }
    }

    private fun setupViewModel(){
        mainViewModel = ViewModelProvider(this, ViewModelFactory(DatabaseHelperImpl(DatabaseBuilder.getDbInstance(applicationContext))))[MainViewModel::class.java]
    }

    override fun onClick(v: View?) {
        if (v?.id==saveButton.id){
            if(edUserName.text.toString().trim() == ""){
                Toast.makeText(this,"User name should not be empty",Toast.LENGTH_SHORT).show()
                return
            }
            if(edUserName.text.toString().trim().length<3){
                Toast.makeText(this,"User name should be in 3 characters",Toast.LENGTH_SHORT).show()
                return
            }
            if(edUserNickName.text.toString().trim() == ""){
                Toast.makeText(this,"User nick name should not be empty",Toast.LENGTH_SHORT).show()
                return
            }
            if(mainViewModel.getUserData().value?.filter { it-> it.userName.lowercase(Locale.ROOT) == edUserName.text.toString().trim().lowercase() }?.size!=null && mainViewModel.getUserData().value?.filter { it-> it.userName.lowercase(Locale.ROOT) == edUserName.text.toString().trim().lowercase() }?.size!! >0){
                Toast.makeText(this,"User name already available !!!",Toast.LENGTH_SHORT).show()
                return
            }
            val user = User(userName = edUserName.text.toString(), userNickname = edUserNickName.text.toString())
            mainViewModel.addUserData(user)
        }
    }
}