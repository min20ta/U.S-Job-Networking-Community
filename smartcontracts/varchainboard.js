
//블록체인
const Web3=require('web3');
const web3=new Web3();
web3.setProvider(new web3.providers.HttpProvider("http://127.0.0.1:8545")); 
const account=web3.eth.accounts[0];
const abi=[{"inputs":[{"internalType":"string","name":"id","type":"string"},{"internalType":"int8","name":"datahash","type":"int8"}],"name":"set","outputs":[],"stateMutability":"nonpayable","type":"function"}];
const addr="0x6b5e53ab52ce28ada5f7852a650c60262618b510";
const chainboard=web3.eth.contract(abi).at(addr);
const crypto = require('crypto');

let blockhash="0";

  

    //블록체인에 넣고 해시값가져오기
    chainboard.set("han2",12,{from:account, gas:"470000"},
    (err,result)=>{
        if(err){
            console.log("블록체인연결오류");
            console.dir(err);
        }
        const waiting=setInterval(()=>{
            const receipt=web3.eth.getTransactionReceipt(result);
            if(receipt){
                blockhash=receipt.blockHash.toString();
                console.log("blockhash: "+blockhash);
                clearInterval(waiting);
            }
            else{console.log("waiting for mining");}
        },1000); }); 
