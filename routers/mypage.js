const express=require('express');
const router=express.Router();
const getConnection = require('../routers/pool.js');

router.use(express.urlencoded({extended:true})) //url확장가능
router.use(express.json()) //json형태받기가능




//andriod_sharedpreferences를 일단 사용함

router.get('/:id',(req,res)=>{

    const id=req.params.id;

 

        getConnection((conn)=>{
     
            console.log("db연결성공");
    

            const exec=conn.query('select coin from users where id=?',[id],
            (err,result)=>{
                conn.release();
                console.log("실행된 쿼리:"+exec.sql);
    
                if(err){
                    console.log('sql실행 error');
                    console.dir(err);
                    return;
                }
                else{
              
                    console.log(result[0].coin);
                    res.send(result[0].coin.toString());
               
                   
                }
              
      
      
        });
        
        });







    });









module.exports = router;