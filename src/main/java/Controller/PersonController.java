package Controller;

import DAO.Person;
import DBManager.DBOperations;
import Request.CreateRequest;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class PersonController
{
  @RequestMapping(value = "/hi", method = RequestMethod.GET)
  public String sayHi() {
    return "Hello from our server";
  }

  @GetMapping("/getPersons")
  List<Person> getPersons()
  {
    //calling dao for data
   return null;
  }

  @RequestMapping(value= "/getPeoples", method = RequestMethod.GET)
    List<Person> getPeoples()
  {
    //calling dao for data
    return null;
  }

  @PostMapping("/insertPerson")
  void insertPerson(@RequestBody CreateRequest request) throws Exception {
     DBOperations.insertPerson(request);
  }

}
