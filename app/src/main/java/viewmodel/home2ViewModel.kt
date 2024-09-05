package viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hometab.MyApi
import com.example.hometab.RetrofitInstance
import dataclass.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class home2ViewModel : ViewModel() {

    private val retrofitInstance = RetrofitInstance.getInstance().create(MyApi::class.java)

    private var _boardlist = MutableLiveData<ArrayList<rvhome2>>()  //여기서만쓸수있게
    val boardlist: LiveData<ArrayList<rvhome2>>  //외부에서 쓸수있게 만든것
    get() = _boardlist

    private var _filteredlist = MutableLiveData<ArrayList<rvhome2>>()  
    val filteredlist: LiveData<ArrayList<rvhome2>>  
        get() = _filteredlist

    var copylist=arrayListOf<rvhome2>()



    fun getAllPost1() = viewModelScope.launch {
       var initlist=retrofitInstance.gethome2()//레트로핏의 함수연결. 데이터보내기
        _boardlist.value=initlist
        copylist=initlist
    }

    init{
        getAllPost1()
    }

    fun addlist(item:rvhome2)= viewModelScope.launch {

        copylist.add(item)
        _boardlist.value=copylist
    }


    var startdate:String="2020-12-12"
    var enddate:String="2020-12-12"

   
    fun givestartvalue(date : String){
        startdate = date
        Log.d("viewdate",startdate)
    }
    fun giveendvalue(date : String){
        enddate = date
        Log.d("viewdate",enddate)
    }

    val format = SimpleDateFormat("yyyy-MM-dd")


    fun setFilter() {
        _filteredlist.value = _boardlist.value?.filter {
            var fordate_startdate =format.parse(startdate)
            var fordate_enddate =format.parse(enddate)

            Log.d("substr",it.write_date.substring(0, 10)) 
            (format.parse(it.write_date.substring(0, 10)).after(fordate_startdate)
                    && format.parse(it.write_date.substring(0, 10)).before(fordate_enddate))

        }?.toMutableList() as ArrayList<rvhome2>?

    }

    private var _addeditem = MutableLiveData<rvhome2>()  //여기서만쓸수있게 감쳐둠
    val addeditem: LiveData<rvhome2> //외부에서 쓸수있게 만든것
        get() = _addeditem

    fun getItem(item:rvhome2) = viewModelScope.launch {
        _addeditem.value = item
    }


    //카테고리클릭시
    private var _categoryclicked = MutableLiveData<List<rvhome2>>()
    val categoryclicked: LiveData<List<rvhome2>>
        get() = _categoryclicked


    fun getCategoryPost(category: String, categorynum:String) = viewModelScope.launch {
        if (categorynum == "1") {
            var category1post = retrofitInstance.gethomecategory1(category)
            _categoryclicked.value = category1post

        } else if (categorynum == "2") {
            var category2post = retrofitInstance.gethomecategory2(category)
            _categoryclicked.value = category2post
            
        } else if (categorynum == "3") {
            var category3post = retrofitInstance.gethomecategory3(category)
            _categoryclicked.value = category3post
        }
    }


    //회원가입
    private var _register = MutableLiveData<Int>()  //굳이 받아온거 뿌릴거아니면 이건 안만들어도되는듯
    val register: LiveData<Int>
        get() = _register


    fun PostRegister(info: person) = viewModelScope.launch {
        retrofitInstance.postregister(info)
    }

    //이메일전송
    private var _emailnumber = MutableLiveData<String>()  //굳이 받아온거 뿌릴거아니면 이건 안만들어도되는듯
    val emailnumber: LiveData<String>
        get() = _emailnumber


    fun PostEmail(info: email) = viewModelScope.launch {
        _emailnumber.value = retrofitInstance.postemail(info).toString()
    }


    //로그인
    fun PostLogin(info: login) = viewModelScope.launch {
        retrofitInstance.postlogin(info)

    }

    //글작성->서버

    fun PostWrtie(info: writecontent) = viewModelScope.launch {
        retrofitInstance.postwrite(info)
    }


    //내정보
    //내정보_내글
    private var _mypage_mywrites = MutableLiveData<List<rvhome2>>()
    val mypage_mywrites: LiveData<List<rvhome2>>
        get() = _mypage_mywrites


    fun getMypageMywritesPost(id: String) = viewModelScope.launch {
        var mypage_mywritespost = retrofitInstance.getmywrites(id)
        _mypage_mywrites.value = mypage_mywritespost
    }


    //내정보_내글_숨기기버튼클릭정보 서버로
    fun PostMypageMyWritesHidden(id: String, info: writecontent) = viewModelScope.launch {
        var mypage_mywrites_hiddenpost = retrofitInstance.postmyhiddenwrites(id, info)
    }



    //내정보_내포인트
    private var _coin = MutableLiveData<String>()
    val coin: LiveData<String>
        get() = _coin

    fun GetMypageCoin(id: String)= viewModelScope.launch {
        _coin.value=retrofitInstance.getmypagecoin(id)
    }


    //숨김게시판
     //글받아오기
    private var _hiddenpage = MutableLiveData<List<hiddendata>>()
    val hiddenpage: LiveData<List<hiddendata>>
        get() = _hiddenpage



    fun gethiddenpage() = viewModelScope.launch {
        var hiddenpagewrites = retrofitInstance.getmyhiddenpage()
        _hiddenpage.value = hiddenpagewrites
    }


      //구입정보보내기
    fun PostBoughtHiddenpage(id: String, info: writecontent) = viewModelScope.launch {
            retrofitInstance.postboughthiddenpage(id, info)
    }

    //비밀번호변경
    fun PostChangePassword(info:login) = viewModelScope.launch {
        retrofitInstance.postchangepassword(info)
    }





}


