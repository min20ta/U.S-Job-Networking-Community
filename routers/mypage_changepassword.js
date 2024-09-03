const express=require('express');
const router=express.Router();
const path=require('path');
const getConnection = require('../routers/pool.js');
const bodyParser = require('body-parser');


router.use(express.urlencoded({extended:true})) //url확장가능
router.use(express.json()) //json형태받기가능









router.post('/',(req,res)=>{
    const body=req.body;
    const id=body.id;
    const newpassword=body.password;
    
    
    getConnection((conn)=>{
       
        console.log("db연결성공");
    

        const exec=conn.query('update users set password=? where id=?',[newpassword,id],
        (err,rows)=>{
            conn.release();
            console.log("실행된 쿼리:"+exec.sql);

            if(err){
                console.log('sql실행 error');
                console.dir(err);
                return;
            }
            else{
                console.log("비밀번호변경");
               
        
            }
           

        });
  



        
        
            
    
        
    });




    });

module.exports = router;