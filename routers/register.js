const express=require('express');
const router=express.Router();
const getConnection = require('../routers/pool.js');
const path=require('path');
const bodyParser = require('body-parser');


router.use(express.urlencoded({extended:true})) //url확장가능
router.use(express.json()) //json형태받기가능


//회원가입
router.post('/',(req,res)=>{
    const body=req.body;
    const id=body.id;
    const password=body.password;
    const name=body.name;
    const phonenumber=body.phonenumber;

    getConnection((conn)=>{
     
        console.log("db연결성공");
    

        conn.query('insert into users(id,password,name,phonenumber)values(?,?,?,?)',
        [id,password,name,phonenumber],
        (err,result)=>{

            conn.release();

            if(err) {
                console.log('sql실행 error');
                console.dir(err);
            }
            if(result){
                console.log('회원가입성공');
                
            }
            else{
                console.log('회원가입실패');
            }


        
        
            
    
        
    });




    })
  
});

module.exports = router;