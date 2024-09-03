const express=require('express');
const router=express.Router();
const getConnection = require('../routers/pool.js');

router.use(express.urlencoded({extended:true})) //url확장가능
router.use(express.json()) //json형태받기가능




router.get('/',(req,res)=>{
    


    getConnection((conn)=>{
     
        console.log("db연결성공");

        const exec=conn.query('select id,category1,category2,category3,jobstatus,content,write_date from writeboard where hidden="no" order by board_num DESC',
        (err,result)=>{
            conn.release();
            console.log("실행된 쿼리:"+exec.sql);

            if(err){
                console.log('sql실행 error');
                console.dir(err);
                return;
            }
            else{
                // res.json({id:id})}
                res.send(result);
                console.log("id보냄냄");
           
               
            }
          
  
  
    });
    
    });});
module.exports = router;