const express = require('express');
const path=require('path');
const static=require('serve-static');  //이게 가장위 루트

//로그인유지(쿠키,세션이용)
const session=require('express-session');
const mysqlstore = require("express-mysql-session")(session);
const cookieparser=require('cookie-parser');
const dbconfig=require('../config/dbconfig.json');

const sessionstore=new mysqlstore({
  connectionLimit:10,
  host: dbconfig.host,
  user: dbconfig.user,
  password:dbconfig.password,
  database:dbconfig.database,
  debug:false

});


//블록체인
const Web3=require('web3');
const web3=new Web3();
web3.setProvider(new web3.providers.HttpProvider("http://127.0.0.1:8545")); //안되면 로컬호스트로 바꾸기
const send_account="0x00B828CD483d8B8E3B758f31d2BD91c9fd6bBBE6";
const receive_account="0x46F0C5AbE5770246EB69b44BBe02E10B5FaF7d71";
const privateKey=Buffer.from('privateKey','hex');
const Tx = require('ethereumjs-tx');


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
const writerouter = require('../routers/write');
app.use('/register',registerrouter);
app.use('/login',loginrouter);
app.use('/mail',mailrouter);
app.use('/home',homerouter);
app.use('/write',writerouter);





app.get('/main',(req,res)=>{
  
  web3.eth.getTransactionCount(send_account, (err, txCount) =>{ //txcount=nonce

    const txObject ={ //nonce,gaslimit,gasprice,to받는계좌,value이더리움값
      nonce: web3.utils.toHex(txCount),
      gasLimit: web3.utils.toHex(100000),
      gasPrice: web3.utils.toHex(web3.utils.toWei('10','gwei')),
      to: receive_account,
      value: '0x2386f26fc10000'
    };

    const tx = new Tx(txObject);
    tx.sign(privateKey);  //개인키를 이용한 sign

    const serializedTx = tx.serialize();
    const raw = '0x' + serializedTx.toString('hex');


    web3.eth.sendSignedTransaction(raw)  //sign된 트랜잭션을 이더리움으로 보냄. hash,receipt가 리턴됨
     .once('transactionHash', (hash) => {
       console.info('transactionHash', 'http://127.0.0.1:8545' + hash);
     })
     .once('receipt', (receipt) => {  //사용된 가스량,계산된 가스량,해쉬값..
       console.info('receipt', receipt);
    }).on('error', console.error);
  });

  });








app.listen(3000);