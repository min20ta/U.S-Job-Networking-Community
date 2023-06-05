const express=require('express');
const router=express.Router();
const path=require('path');
const poolmodule = require('../routers/pool.js');
const bodyParser = require('body-parser');


router.use(express.urlencoded({extended:true})) //url확장가능
router.use(express.json()) //json형태받기가능





router.post('/',(req,res)=>{
    const body=req.body;
    const id=body.id;
    const password=body.password;
    
    
    poolmodule.getConnection((conn)=>{
       
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
                req.session.id=id;
                req.session.is_logined=true;
                req.session.save();
               
            }
            else{
                console.log('로그인실패');
            }


        });
  



        
        
            
    
        
    });




    });

module.exports = router;