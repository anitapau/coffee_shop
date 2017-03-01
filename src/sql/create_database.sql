/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  anitapau
 * Created: Feb 4, 2017
 */

create table coffeeShop (
    id integer primary key,
    name varchar(40),
    city varchar(40),
    state varchar(40),
    zip integer,
    phone integer,
    opentime integer,
    closetime integer,
    description varchar(100)

    
);

create table review (
    reviewid integer primary key,
    shopid   integer,
    reviews   varchar(100),
    rank     integer
    
);

