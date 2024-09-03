const express=require('express');
const router=express.Router();
const getConnection = require('../routers/pool.js');

router.use(express.urlencoded({extended:true})) //url확장가능
router.use(express.json()) //json형태받기가능


//로그인유지(쿠키,세션이용)
const session=require('express-session');
const mysqlstore = require("express-mysql-session")(session);
const cookieparser=require('cookie-parser');
const dbconfig=require('../config/dbconfig.json');

const sessionstore=new mysqlstore({
  connectionLimit:10,   //최대커넥션수
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






//로그아웃
router.get('/', function (req, res, next) {
  req.session.destroy(); // 로그인 세션정보를 삭제
  res.redirect('/'); // 로그아웃시 메인페이지로 이동
  console.log("로그아웃되었습니다")
});


//req.session/destory(funtion(err){
//     콜백함수
//  response.redirect('/')
// })

//비밀번호변경












module.exports = router;