const express=require('express');
const router=express.Router();
const getConnection = require('../routers/pool.js');
const crypto = require('crypto');
const BigNumber = require('bignumber.js'); 


router.use(express.urlencoded({extended:true})) //url확장가능
router.use(express.json()) //json형태받기가능

//블록체인
const Web3=require('web3');
const web3=new Web3();
web3.setProvider(new web3.providers.HttpProvider("http://127.0.0.1:8545")); //안되면 로컬호스트로 바꾸기
const account=web3.eth.accounts[0];
const abi=[{"inputs":[{"internalType":"string","name":"id","type":"string"},{"internalType":"string","name":"datahash","type":"string"}],"name":"set","outputs":[],"stateMutability":"nonpayable","type":"function"}];
const addr="0x2a726fee1121936558d5989f3c60401b950eb765";
const chainboard=web3.eth.contract(abi).at(addr);


// 게시글 작성
router.post('/', (req, res) => {
    const body=req.body;
    const id =body.id;  //받아야하나?
    const category1=body.category1;
    const category2=body.category2;
    const category3=body.category3;
    const jobstatus=body.jobstatus;
    const content=body.content;
    const savestatus=body.savestatus;

    const contenthash=crypto.pbkdf2Sync(content,crypto.randomBytes(2).toString('hex'), 1, 2, 'sha512').toString('hex').toString();
    let blockhash;

    getConnection((conn)=>{
     
        console.log("db연결성공");


    
        //블록체인에 넣고 해시값가져오기
        chainboard.set(id,contenthash,{from:account, gas:"470000"},
        (err,result)=>{
            if(err){
                console.log("블록체인연결오류");
                console.dir(err);
            }
            const waiting=setInterval(function(){
                const receipt=web3.eth.getTransactionReceipt(result);
                if(receipt){
                    blockhash=receipt.transactionHash.toString();
                    console.log("blockhash: "+ blockhash);
                    clearInterval(waiting);

                    conn.query('insert into writeboard(id,category1,category2,category3,jobstatus,content,savestatus,blockhash)values(?,?,?,?,?,?,?,?)',[id,category1,category2,category3,jobstatus,content,savestatus,blockhash],
                    (err,result)=>{

                        conn.release();
            
                        if(err) {
                            console.log('sql실행 error');
                            console.dir(err);
                        }
                        if(result){
                            console.log('글쓰기저장성공');
                            res.json({ message: '게시글이 작성되었습니다.' });
                            
                        }
                        else{
                            console.log('글쓰기저장실패');
                            res.json({ message: '게시글 저장이 실패했습니다.' });
                        }
                  
                     });

                }
                else{console.log("waiting for mining");}
            }); });


   
    
      

    });
});

module.exports = router;


 
