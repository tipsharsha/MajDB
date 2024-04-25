package com.oop.majdb;


import org.springframework.data.repository.CrudRepository;
public interface PersonRepo extends CrudRepository<Person, Integer>{
    Person findByEmail(String email);
    Person findByUserID(int userID);
    Person findByEmailAndPassword(String email, String password);
    Person findByUserIDAndPassword(int userID, String password);
    Person findByEmailAndUserID(String email, int userID);
    Person findByEmailAndUserIDAndPassword(String email, int userID, String password);
    Person findByEmailAndPasswordAndUserID(String email, String password, int userID);
    Person findByUserIDAndPasswordAndEmail(int userID, String password, String email);
    Person findByUserIDAndEmail(int userID, String email);
    Person findByUserIDAndEmailAndPassword(int userID, String email, String password);
}
