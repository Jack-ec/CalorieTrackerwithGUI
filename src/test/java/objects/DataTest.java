package objects;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class DataTest {

    @Test
    void storeNewUser() {
        //Arrange
        Data d = new Data();
        boolean expected = true;
        //Act
        boolean actual = d.storeNewUser("phil", 5, 5,5,5, "fat");
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void storeNewFood() {
        //Arrange
        Data d = new Data();
        boolean expected = true;
        //Act
        boolean actual = d.storeNewFood("taco", 5, 5,5,5);
        //Assert
        assertEquals(expected, actual);
    }

    @Test
    void getAllUsers() {
        //Arrange
        Data d = new Data();
        d.storeNewUser("phil", 5, 5,5,5, "fat");
        d.storeNewUser("jane", 5, 5,5,5, "fat");
        ArrayList<User> expected = new ArrayList<>();
        User u1 = new User("phil", 5, 5, 5, 5 , "fat");
        User u2 = new User("jane", 5, 5, 5, 5 , "fat");
        expected.add(u1);
        expected.add(u2);
        //Act
        ArrayList<User> actual = d.getAllUsers();
        boolean result = actual.equals(expected);
        //Assert
        assertTrue(result);
    }

    @Test
    void getAllFoods() {
        //Arrange
        Data d = new Data();
        d.storeNewFood("chicken", 5, 5,5,5);
        d.storeNewFood("taco", 5, 5,5,5);
        ArrayList<Food> expected = new ArrayList<>();
        Food f1 = new Food("chicken", 5, 5, 5, 5);
        Food f2 = new Food("taco", 5, 5, 5, 5);
        expected.add(f1);
        expected.add(f2);
        //Act
        ArrayList<Food> actual = d.getAllFoods();
        boolean result = actual.equals(expected);
        //Assert
        assertTrue(result);
    }

    @Test
    void getUser() {
        //Arrange
        Data d = new Data();
        d.storeNewUser("mary", 5,5,5,5,"fat");
        User expected = new User("mary",5,5,5,5, "fat");
        //Act
        User actual = d.getUser("mary");
        //Arrange
        assertEquals(expected, actual);
    }

    @Test
    void getFood() {
        //Arrange
        Data d = new Data();
        d.storeNewFood("taco", 5,5,5,5);
        Food expected = new Food("taco",5,5,5,5);
        //Act
        Food actual = d.getFood("taco");
        //Arrange
        assertEquals(expected, actual);
    }

    @Test
    void userExists() {
        //Arrange
        Data d = new Data();
        d.storeNewUser("mary", 5,5,5,5,"fat");
        boolean expected = true;
        //Act
        boolean actual = d.userExists("mary");
        //Arrange
        assertEquals(expected, actual);
    }

    @Test
    void foodExists() {
        //Arrange
        Data d = new Data();
        d.storeNewFood("taco", 5,5,5,5);
        boolean expected = true;
        //Act
        boolean actual = d.foodExists("taco");
        //Arrange
        assertEquals(expected, actual);
    }

    @Test
    void deleteUser() {
        //Arrange
        Data d = new Data();
        d.storeNewUser("jill", 5,5,5,5, "fat");
        Food expected = null;
        //Act
        d.deleteUser("jill");
        //Arrange
        assertEquals(expected, d.getFood("jill"));
    }

    @Test
    void deleteFood() {
        //Arrange
        Data d = new Data();
        d.storeNewFood("taco", 5,5,5,5);
        Food expected = null;
        //Act
        d.deleteFood("taco");
        //Arrange
        assertEquals(expected, d.getFood("taco"));
    }
}