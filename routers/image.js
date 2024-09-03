const express=require('express');
const multer=require('multer');  //이미지받기
const router=express.Router();
const getConnection = require('./pool.js');
const path=require('path');
const bodyParser = require('body-parser');


router.use(express.urlencoded({extended:true})) //url확장가능
router.use(express.json()) //json형태받기가능



//이미지받아오기
  
  const upload = multer({ storage:multer.diskStorage({ //저장방식
    destination(req, file, done) { // 저장되는곳
      done(null, 'idfileImages/');  //경로위치조심
    },
     filename(req, file, done) {  //저장되는 이름지정
      const ext = path.extname(file.originalname);
      const fileName = `${path.basename(
        file.originalname,
        ext
      )}_${Date.now()}${ext}`;
      done(null, fileName);
    },
  }), 
    limits: { fileSize: 5 * 1024 * 1024 } });




// 이미지를 서버에 업로드

router.post("/", upload.single('idfile'), (req, res)=>{

  //이미지->req.file로 들어오고   //여러이미지는 req.files
  //나머지 데이터는 req.body로 들어옴


    const body = req.body
    const id=body.id;
    const image=req.file.path
   

    getConnection((conn)=>{
       
        console.log("db연결성공");
    

        const exec=conn.query('insert into usersimage(id,image)values(?,?)',
        [id,image],
        (err,result)=>{
            conn.release();
            console.log("실행된 쿼리:"+exec.sql);

            if(err){
                console.log('sql실행 error');
                console.dir(err);
                return;
            }
       
            if(result){
                console.log(image);
                console.log(id);
                console.log('이미지넣기성공');
                res.send('upload!');
            }


        });



  
    
});
});
module.exports = router;











