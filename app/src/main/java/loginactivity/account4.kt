
package loginactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hometab.R
import com.example.hometab.itshome
import information.information1
import kotlinx.android.synthetic.main.activity_account4.*


class account4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account4)

        tohome.setOnClickListener() {
            val intent = Intent(this, information1::class.java)
            startActivity(intent)

        }
    }
}