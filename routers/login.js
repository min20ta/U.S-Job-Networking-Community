const express=require('express');
const router=express.Router();
const path=require('path');
const getConnection = require('../routers/pool.js');
const bodyParser = require('body-parser');


router.use(express.urlencoded({extended:true})) //url확장가능
router.use(express.json()) //json형태받기가능




//로그인유지(쿠키,세션이용)
const session=require('express-session');
const mysqlstore = require("express-mysql-session")(session);
const cookieparser=require('cookie-parser');
const dbconfig=require('../config/dbconfig.json');

const sessionstore=new mysqlstore({
  connectionLimit:20,   //최대커넥션수
  host: dbconfig.host,
  user: dbconfig.user,
  password:dbconfig.password,
  database:dbconfig.database,
  debug:false

});

router.use(cookieparser());  //?
router.use( // 세션
  session({
    secret: "my key",
    resave: false,
    saveUninitialized: true,
    store:sessionstore,
  })
);




router.post('/',(req,res)=>{
    const body=req.body;
    const id=body.id;
    const password=body.password;
    
    
    getConnection((conn)=>{
       
        console.log("db연결성공");
    

        const exec=conn.query('select id from users where `id`=? and `password`=?',[id,password],
        (err,rows)=>{
            conn.release();
            console.log("실행된 쿼리:"+exec.sql);

            if(err){
                console.log('sql실행 error');
                console.dir(err);
                return;
            }
            if(rows.length>0){
                console.log("아이디[%s]와 패쓰워드 일치",id);
                req.session.name=id;
                req.session.is_logined=true;
                req.session.save();
                console.log("세션이 생성되었습니다",req.session);  //나중에 지우기
               
            }
            else{
                console.log('로그인실패');
            }


        });
  



        
        
            
    
        
    });




    });

module.exports = router;