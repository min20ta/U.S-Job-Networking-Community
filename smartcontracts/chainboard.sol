// SPDX-License-Identifier: MIT
pragma solidity ^0.8.13;
contract chainboard{

string data;
string owner;

  

    //블록에 저장
    function set(string memory id,string memory datahash) public {
        owner=id;
        data=datahash;
     
    }




}