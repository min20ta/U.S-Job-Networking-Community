package com.example.hometab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dataclass.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface MyApi {

    //최신글게시판-전체데이터가져오기
    @GET("home2")
   suspend fun gethome2():ArrayList<rvhome2>  //앞함수=서버로보내는거 : 뒤는 서버에서 다시 받는 방식


    //카테고리누르면 해당게시판으로
    @GET("home/category/{category}")
    suspend fun gethomecategory1(
        @Path("category") category: String
    ): List<rvhome2>

    @GET("home/category2/{category}")
   suspend fun gethomecategory2(
        @Path("category") category: String
    ): List<rvhome2>

    @GET("home/category3/{category}")
    suspend fun gethomecategory3(
        @Path("category") category: String
    ): List<rvhome2>


    //글쓰기저장버튼누르면 서버로 정보넘기기
    @POST("write")
    suspend fun postwrite(@Body body: writecontent)

    //회원가입
    @POST("register")
    suspend fun postregister(@Body body: person)

    //이메일주소
    @POST("mail")
    suspend fun postemail(@Body body: email): String

    //사원증사진보내기
    @Multipart
    @POST("image")
    fun sendImage(
        @Part imagefile: MultipartBody.Part,
        @Part("id") id: RequestBody
    ): Call<String>


    //로그인
    @POST("login")
    suspend fun postlogin(@Body body: login)




    //마이페이지-내포인트보여주기
    @GET("mypage/{id}")
    suspend fun getmypagecoin(
        @Path("id") id: String
    ):String


    //마이페이지-내글보여주기
    @GET("mywrites/{id}")
    suspend fun getmywrites(
        @Path("id") id: String
    ):List<rvhome2>

    //마이페이지_내글보여주기_숨기기버튼
    @POST("mywrites/{id}")
    suspend fun postmyhiddenwrites(
        @Path("id") id: String,
        @Body body: writecontent
    )

    //마이페이지_숨김게시판_글가져오기
    @GET("myhiddenpage")
    suspend fun getmyhiddenpage(): List<hiddendata>

    //마이페이지_숨김게시판_구입정보보내기
    @POST("myhiddenpage/{id}")
    suspend fun postboughthiddenpage(
        @Path("id") id: String,
        @Body body: writecontent
    )

    //비밀번호변경
    @POST("changepassword")
    suspend fun postchangepassword(
        @Body body: login
    )


}
