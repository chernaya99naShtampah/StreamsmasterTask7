import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class HouseTest
{
    @Test
    public void testHouse1() throws Exception
    {
        Data date1 = new Data(12, 3, 2004);
        Data date2 = new Data(13, 4, 2007);
        Data date3 = new Data(23, 1, 2002);
        Data date4 = new Data(10, 1, 1986);
        Data date5 = new Data(30, 5, 1974);
        Data date6 = new Data(28, 11, 1999);

        Person person1 = new Person("Ivanov", "Ivan", "Ivanovich", date1);
        Person person2 = new Person("Dmitriev", "Dmitrii", "Dmitrievich", date2);
        Person person3 = new Person("Liliev", "Artem", "Vladimirovich", date3);
        Person person4 = new Person("Byinov", "Artemii", "Alekseevich", date4);
        Person person5 = new Person("Sidorov", "Petr", "Sergeevich", date5);
        Person person6 = new Person("Sergeev", "Sergei", "Andreevich", date6);

        List<Person> list1 = new ArrayList<>(Arrays.asList(person1, person2, person3));
        List<Person> list2 = new ArrayList<>(Arrays.asList(person4, person5, person6));

        Flat flat1 = new Flat(45, 56.2, list1);
        Flat flat2 = new Flat(74, 14.2, list2);

        List<Flat> l = new ArrayList<>(Arrays.asList(flat1, flat2));

        House house = new House("451261", "Omsk", "Lenina", "5a", person1, l);

        assertTrue("451261".equals(house.getNumber()) && "Omsk".equals(house.getAddressCity()) && "Lenina".equals(house.getAddressStreet())
                && "5a".equals(house.getAddressNumber()) && person1.equals(house.getAdmin()) && l.equals(house.getFlats()));
    }

    @Test(expected = Exception.class)
    public void testHouse2() throws Exception
    {
        Data date = new Data(12, 3, 2004);

        Person person = new Person("Ivanov", "Ivan", "Ivanovich", date);

        List<Person> list = new ArrayList<>(Arrays.asList(person));

        Flat flat = new Flat(45, 56.2, list);

        List<Flat> l = new ArrayList<>(Arrays.asList(flat));

        House house = new House("451261", "", "Lenina", "5a", person, l);
    }

    @Test(expected = Exception.class)
    public void testHouse3() throws Exception
    {
        Data date = new Data(12, 3, 2004);

        Person person = new Person("Ivanov", "Ivan", "Ivanovich", date);

        List<Person> list = new ArrayList<>(Arrays.asList(person));

        Flat flat = new Flat(45, 56.2, list);

        List<Flat> l = new ArrayList<>(Arrays.asList(flat));

        House house = new House("451261", "", "Lenina", "5a", person, null);
    }
}