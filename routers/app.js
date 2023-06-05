const express = require('express');
const path=require('path');
const static=require('serve-static');  //이게 가장위 루트

//로그인유지(쿠키,세션이용)
const session=require('express-session');
const mysqlstore = require("express-mysql-session")(session);
const cookieparser=require('cookie-parser');
const poolmodule = require('../routers/pool.js');
const dbconfig=require('../config/dbconfig.json');



const sessionstore=new mysqlstore({
  connectionLimit:10,
  host: dbconfig.host,
  user: dbconfig.user,
  password:dbconfig.password,
  database:dbconfig.database,
  debug:false

});

const app = express();
//환경설정
app.use(express.urlencoded({extended:true})) //url확장가능
app.use(express.json()) //json형태받기가능
app.use('/routers',static(path.join(__dirname,'routers'))); //디렉토리지정
app.use(cookieparser());
app.use( // 세션
  session({
    secret: "my key",
    resave: false,
    saveUninitialized: true,
    store:sessionstore,
  })
);



//라우터
const registerrouter = require('../routers/register');
const loginrouter = require('../routers/login');
const mailrouter = require('../routers/mail');
const homerouter = require('../routers/home');
app.use('/register',registerrouter);
app.use('/login',loginrouter);
app.use('/mail',mailrouter);
app.use('/home',homerouter);











app.listen(3000);