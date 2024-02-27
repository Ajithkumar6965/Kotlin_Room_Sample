package me.ajith.learning.roomsample.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.ajith.learning.roomsample.R

class UserAdapter(private var users:ArrayList<User>) : RecyclerView.Adapter<UserAdapter.UserDataViewHolder>() {
    class UserDataViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        private var tvUserName:TextView
        private var tvUserNickName:TextView
        init {
            tvUserName = itemView.findViewById(R.id.tvUserName)
            tvUserNickName = itemView.findViewById(R.id.tvUserNickName)
        }
        fun bind(user: User){
            tvUserName.text = user.userName
            tvUserNickName.text = user.userNickname
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDataViewHolder {
        return UserDataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item_layout,parent,false))
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserDataViewHolder, position: Int) = holder.bind(users[position])

    fun addUsers(newUsers: List<User>){
        users.clear()
        users.addAll(newUsers)
    }


    fun addUser(user: User){
        users.add(user)
    }
}