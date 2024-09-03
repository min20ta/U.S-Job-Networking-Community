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
const imagerouter = require('../routers/image');
const homerouter = require('../routers/home');
const home2router = require('../routers/home2');
const writerouter = require('../routers/write');
const mypagerouter = require('../routers/mypage');
const mypagerouter_logout = require('../routers/logout');
const mypagerouter_mywrites = require('../routers/mypage_mywrites');
const mypagerouter_hiddenpage = require('../routers/mypage_myhiddenpage');
const mypagerouter_changepassword = require('../routers/mypage_changepassword');
// const mypagerouter_mypoint = require('../routers/mypage_mypoint');
app.use('/register',registerrouter);
app.use('/login',loginrouter);
app.use('/mail',mailrouter);
app.use('/home',homerouter);
app.use('/home2',home2router);
app.use('/write',writerouter);
app.use('/image',imagerouter);
app.use('/mypage',mypagerouter);
app.use('/logout',mypagerouter_logout);
app.use('/mywrites',mypagerouter_mywrites);
app.use('/myhiddenpage',mypagerouter_hiddenpage);
app.use('/changepassword',mypagerouter_changepassword);
// app.use('/mypoint',mypagerouter_mypoint);













app.listen(3000);