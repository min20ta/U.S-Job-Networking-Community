const express=require('express');
const router=express.Router();
const getConnection = require('../routers/pool.js');

router.use(express.urlencoded({extended:true})) //url확장가능
router.use(express.json()) //json형태받기가능






//내가쓴글
router.get('/:id',(req,res)=>{
    const {id}=req.params;

    getConnection((conn)=>{
     
        console.log("db연결성공");

        const exec=conn.query('select id,category1,category2,category3,jobstatus,content,write_date from writeboard where id=? order by board_num DESC',[id],
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
                console.log(result);
                console.log("게시글보냄");
           
               
            }
          
  
  
    });
    
    });});






//내가쓴글_숨기기버튼누른것 
router.post('/:id',(req,res)=>{
    const {id}=req.params;
    const body=req.body;
    const category1=body.category1;
    const category2=body.category2;
    const category3=body.category3;
    const content=body.content;

    getConnection((conn)=>{
     
        console.log("db연결성공");

        const exec=conn.query('update writeboard set hidden="Yes" where id=? and category1=? and category2=? and category3=? and content=?',[id,category1,category2,category3,content],

        (err,result)=>{

            conn.release();
            console.log("실행된 쿼리:"+exec.sql);

            if(err){
                console.log('sql실행 error');
                console.dir(err);
                return;
            }
            else{
                
                console.log("숨김게시물 업데이트완료");
           
               
            }
          
  
  
    });
    
    });});


















module.exports = router;