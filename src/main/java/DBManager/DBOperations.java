package DBManager;

import DAO.Person;
import Request.CreateRequest;

import java.sql.*;
import java.util.*;

public class DBOperations
{
  private static volatile Connection connection;

  public static Connection getConnection() throws SQLException
  {
      if(connection == null)
      {
        synchronized (DBOperations.class)
        {
          if(connection == null)
          {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/person", "prsaha", "prasenjit");
          }
        }
      }
      return connection;
  }

  public static void closeConnection() throws SQLException
  {
    if(connection != null)
    {
      synchronized (DBOperations.class)
      {
        if(connection != null)
        {
          connection.close();
          connection = null;
        }
      }
    }
  }

  public static void insertPerson(CreateRequest request) throws Exception
  {
      Person person= new Person(request.getName(), request.getAge(), request.getAddress());
      PreparedStatement preparedStatement= connection.prepareStatement("INSERT INTO person VALUES (null, ?, ?, ?)");
      preparedStatement.setString(1,request.getName());
      preparedStatement.setInt(2,request.getAge());
      preparedStatement.setString(3, request.getAddress());
      //Statement statement = connection.createStatement();
      //int rowsAffected = statement.executeUpdate("INSERT INTO person values(request.getName(), request.getAge(), request.getAddress());
      int rowsAffected = preparedStatement.executeUpdate();

      if(rowsAffected > 0)
        System.out.println("Insertion successfully done");
      else
        System.out.println("Couldn't insert into the table");
  }

  public static Person getPersonById(Integer id)
  {
      return null;
  }


  public Boolean deletePerson(Integer id)
  {
      return null;
  }

  public Boolean updatePerson(Person person)
  {
      return null;
  }

  public static List<Person> getPersons() throws SQLException {
    getConnection();

    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM person");
    List<Person> persons = new ArrayList<>();
    while(resultSet.next()){

      String name = resultSet.getString(2);
      int age = resultSet.getInt(3);
      String add = resultSet.getString(4);

      int id = resultSet.getInt(1);

      Person person = new Person(id, name, age, add);

      System.out.println(person);

      persons.add(person);
    }

    closeConnection();
    return persons;
  }
}
