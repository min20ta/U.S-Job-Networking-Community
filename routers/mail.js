const nodemailer=require('nodemailer');
const express=require('express');
const router=express.Router();

router.use(express.urlencoded({extended:true})) //url확장가능
router.use(express.json()) //json형태받기가능

let generateRandom = function (min, max) {
    var ranNum = Math.floor(Math.random()*(max-min+1)) + min;
    return ranNum;
}

router.post('/',(req,res)=>{
    const body=req.body;
    const email=body.email;
 
    const transporter=nodemailer.createTransport({      
    //gmail과 naver정책이 바뀌어서 smtp쓰려면 프리미엄가입?이런거 해야해서 개발테스트용메일프로그램이용함
    host:"smtp.mailtrap.io",
    port:2525,
    secure:false,
    auth:{
        user: "2b91a5635afac8", 
        pass: "c45208895bded3",
    }});

    const number = generateRandom(111111,999999);
    
    let mailOptions = {
        
        from: "mailtrap@naver.com", //송신할 이메일
        to: email, //수신할 이메일
        subject: "AOB 인증메일",
        text: "숫자 6자리를 입력해주세요 "+number,
       
    };
   
    transporter.sendMail(mailOptions,function(err,res){
        if(err){
            console.log("이메일발송오류");
            console.dir(err);
        }else{ 
            console.log("이메일발송성공")  //안드로이드 터미널에 나옴
            console.log(number);
            }
           


        });

        res.send(number.toString());

        //현직자디비에...
    

});


module.exports = router;