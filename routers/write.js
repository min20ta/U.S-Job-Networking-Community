const express=require('express');
const router=express.Router();
const getConnection = require('../routers/pool.js');

router.use(express.urlencoded({extended:true})) //url확장가능
router.use(express.json()) //json형태받기가능




// 게시글 작성
router.post('/', (req, res) => {
    const body=req.body;
    const id =id;  //받아야하나?
    const category1=body.category1;
    const category2=body.category2;
    const category3=body.category3;
    const jobstatus=body.jobstatus;
    const content=body.content;
    const savestatus=body.savestatus;

    getConnection((conn)=>{
     
        console.log("db연결성공");

        conn.query('insert into writeboard(id,category1,category2,category3,jobstatus,content,savestatus)values(?,?,?,?,?,?,?)',[category1,category2,category3,jobstatus,content,savestatus],
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




    });
});

  module.exports = router;