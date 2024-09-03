const express=require('express');
const router=express.Router();
const getConnection = require('../routers/pool.js');

router.use(express.urlencoded({extended:true})) //url확장가능
router.use(express.json()) //json형태받기가능




router.get('/category/:category',(req,res)=>{
    const {category}=req.params;

    getConnection((conn)=>{
     
        console.log("db연결성공");

        const exec=conn.query('select id,category1,category2,category3,jobstatus,content,write_date from writeboard where category1=? order by board_num DESC',[category],
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
                console.log("게시글보냄");
           
               
            }
          
  
  
    });
    
    });});

    router.get('/category2/:category',(req,res)=>{
        const {category}=req.params;
    
        getConnection((conn)=>{
         
            console.log("db연결성공");
    
            const exec=conn.query('select id,category1,category2,category3,jobstatus,content,write_date from writeboard where category2=? order by board_num DESC',[category],
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
                    console.log("게시글보냄");
               
                   
                }
              
      
      
        });
        
        });});

        router.get('/category3/:category',(req,res)=>{
            const {category}=req.params;
        
            getConnection((conn)=>{
             
                console.log("db연결성공");
        
                const exec=conn.query('select id,category1,category2,category3,jobstatus,content,write_date from writeboard where category3=? order by board_num DESC',[category],
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
                        console.log("게시글보냄");
                   
                       
                    }
                  
          
          
            });
            
            });});



       

            
module.exports = router;



















