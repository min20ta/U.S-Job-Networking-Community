const express = require('express');
const path=require('path');
const static=require('serve-static');  //이게 가장위 루트



const app = express();
//환경설정
app.use(express.urlencoded({extended:true})) //url확장가능
app.use(express.json()) //json형태받기가능
app.use('/routers',static(path.join(__dirname,'routers'))); //디렉토리지정




//라우터
const registerrouter = require('../routers/register');
const loginrouter = require('../routers/login');
const mailrouter = require('../routers/mail');
app.use('/register',registerrouter);
app.use('/login',loginrouter);
app.use('/mail',mailrouter);











app.listen(3000);