const express=require('express');
const mysql=require('mysql');
const router=express.Router();
const getConnection = require('../routers/pool.js');

router.use(express.urlencoded({extended:true})) //url확장가능
router.use(express.json()) //json형태받기가능






//숨김글게시판
    //이미구입한것들도처리+내가가진코인
router.get('/',(req,res) => {
    
 

    getConnection((conn)=>{
     
        console.log("db연결성공");

        
        const exec=conn.query('select a.id,a.category1,a.category2,a.category3,a.jobstatus,a.content,a.write_date,b.buyer from (select id,category1,category2,category3,jobstatus,content,board_num,write_date from writeboard where hidden="Yes" order by board_num DESC) a left join buyhiddenpage b on a.board_num=b.board_num order by a.board_num DESC',

        (err,result)=>{
            conn.release();
            console.log("실행된 쿼리:"+exec.sql);

            if(err){
                console.log('sql실행 error');
                console.dir(err);
                
            }
            else{
                
             
                res.send(result);
                
                
           
               
            }
          
  
  
    });
    
    });});





//구입한 게시물 정보
router.post('/:id',(req,res)=>{
    const{id}=req.params;

    const body=req.body;
    const writerid=body.id;
    const category1=body.category1;
    const category2=body.category2;
    const category3=body.category3;
    const content=body.content;
    
       getConnection((conn)=>{
         
            console.log("db연결성공");
        
            const sql1='insert into buyhiddenpage(buyer,board_num) values(?,(select board_num from writeboard where id=? and category1=? and category2=? and category3=? and content=?));';
            const sql1content=[id,writerid,category1,category2,category3,content];
            const formatsql1=mysql.format(sql1,sql1content);


            const sql2='update users set coin=coin-500 where id=?;';
            const sql2content=[id];
            const formatsql2=mysql.format(sql2,sql2content);
    
            conn.query(formatsql1+formatsql2,
        
            (err,result)=>{
    
                conn.release();
    
                if(err) {
                    console.log('sql실행 error');
                    console.dir(err);
                }
                if(result){
                    console.log('구입성공');
                    
                    
                }
                else{
                    console.log('구입실패');
                }
    
    
            
            
                
        
            
        });
    
    
    
    
        });
      
    });











module.exports = router;