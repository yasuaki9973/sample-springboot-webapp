/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  yasuaki
 * Created: 2017/09/10
 */

CREATE TABLE IF NOT EXISTS users (user_name VARCHAR(30) PRIMARY KEY , encoded_password VARCHAR(100));
CREATE TABLE IF NOT EXISTS customers (id INT PRIMARY KEY AUTO_INCREMENT, first_name VARCHAR(30), last_name VARCHAR(30), user_name VARCHAR(30));
