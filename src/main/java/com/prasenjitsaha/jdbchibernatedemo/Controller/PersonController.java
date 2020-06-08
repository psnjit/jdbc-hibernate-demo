package com.prasenjitsaha.jdbchibernatedemo.Controller;

import com.prasenjitsaha.jdbchibernatedemo.DAO.Person;
import com.prasenjitsaha.jdbchibernatedemo.DBManager.DBOperations;
import com.prasenjitsaha.jdbchibernatedemo.Request.CreateRequest;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.*;

@RestController
public class PersonController
{
  @RequestMapping(value = "/hi", method = RequestMethod.GET)
  public String sayHi() {
    return "Hello from our server";
  }

  @GetMapping("/persons")
  List<Person> getPersons() throws SQLException {
   return DBOperations.getPersons();
  }

  @GetMapping("/person/{id}")
  Person getPersonById(@PathVariable int id) throws SQLException {
    return DBOperations.getPersonById(id);
  }

  @DeleteMapping("/delete/{id}")
  void deletePersonById(@PathVariable int id) throws SQLException {
    DBOperations.deletePerson(id);
  }

  @PutMapping("/update/{id}")
  void deletePersonById(@PathVariable int id, @RequestBody CreateRequest request) throws SQLException {
    DBOperations.updatePerson(id, request);
  }

  @PostMapping("/insert")
  void insertPerson(@RequestBody CreateRequest request) throws Exception {
     DBOperations.insertPerson(request);
  }

}
