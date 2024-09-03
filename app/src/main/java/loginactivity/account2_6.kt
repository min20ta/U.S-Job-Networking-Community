package loginactivity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.hometab.MyApi
import com.example.hometab.RetrofitInstance
import com.example.hometab.databinding.ActivityAccount26Binding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import viewmodel.home2ViewModel
import java.io.File


class account2_6: AppCompatActivity() {


    private lateinit var binding: ActivityAccount26Binding  //xml을 바인딩

    val viewModel: home2ViewModel by lazy {
        ViewModelProvider(this).get(home2ViewModel::class.java)
    }

    lateinit var id:String
    var userid: RequestBody? = null
    var body: MultipartBody.Part? =null

    private val imageresult=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result->
            if(result.resultCode== Activity.RESULT_OK){
                val imageUri=result.data?.data ?:return@registerForActivityResult

                val file= File(absolutelyPath(imageUri,this)) //this=절대경로
//                val requestFile= RequestBody.create(MediaType.parse("image/*"), file) //request형식으로 바꿈
                val requestFile=file.asRequestBody("image/*".toMediaTypeOrNull())
                body=MultipartBody.Part.createFormData("idfile",file.name,requestFile) //form-data형식으로 바꿔줌


                userid= id?.toRequestBody("text/plain".toMediaTypeOrNull())


//                sendImage(body,userid)  //서버로보냄
            } }

    companion object{const val REQ_GALLERY=1}

    private val retrofitInstance = RetrofitInstance.getInstance().create(MyApi::class.java)




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAccount26Binding.inflate(layoutInflater)

        id = intent.getStringExtra("id").toString()

        val view = binding.root
        setContentView(view)



        binding.imagebtn.setOnClickListener { //현직자

            selectGallery()

        }

        binding.nextbtn.setOnClickListener {
            body?.let { it1 -> sendImage(it1,userid) }  //서버로보냄
            val intent = Intent(this, account5::class.java)
            startActivity(intent)
        }
    }



    //갤러리에서 이미지가져오기
    private fun selectGallery(){
        val writePermission= ContextCompat.checkSelfPermission(
            this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        val readPermission= ContextCompat.checkSelfPermission(
            this, android.Manifest.permission.READ_EXTERNAL_STORAGE
        )

        //권한없을때 권한요청
        if (writePermission==PackageManager.PERMISSION_DENIED||
                readPermission==PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE), REQ_GALLERY)
        }
        //권한있을때
        else{
            val intent=Intent(Intent.ACTION_PICK)
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*")
            imageresult.launch(intent)

        }
    }


    //절대경로로 변환
    fun absolutelyPath(path: Uri?, context:Context):String{
        var proj:Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor?=context.contentResolver.query(path!!,proj,null,null,null)
        var index=c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        var result=c?.getString(index!!)
        binding.yourimage.text=result
        return result!!
    }


    //서버로 이미지전송
    fun sendImage(body: MultipartBody.Part, id: RequestBody?){
        if (id != null) {
            retrofitInstance.sendImage(body,id).enqueue(object: Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful)
                    {Toast.makeText(this@account2_6, "이미지 전송 성공", Toast.LENGTH_SHORT).show() }
                    else {
                        Toast.makeText(this@account2_6, "이미지 전송 실패", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<String>, t: Throwable){
                    Log.d("sendimage", t.message.toString())
                }
            })
        }

    }

}
