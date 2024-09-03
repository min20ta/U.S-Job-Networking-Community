
const mysql=require('mysql');
const dbconfig=require('../config/dbconfig.json');


const pool=mysql.createPool({
    connectionLimit:20,
    host: dbconfig.host,
    user: dbconfig.user,
    password:dbconfig.password,
    database:dbconfig.database,
    debug:false,
    multipleStatements: true   
  
  });


  function getConnection(callback) {
    pool.getConnection(function (err, conn) {
      if(!err) {
        callback(conn);
      }
    });
  }
  
  module.exports = getConnection;