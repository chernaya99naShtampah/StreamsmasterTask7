import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CustomSerializeTest
{
    @Test
    public void testSerializePerson1() throws Exception
    {
        Data date = new Data(12, 3, 2004);

        Person person = new Person("Ivanov", "Ivan", "Ivanovich", date);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(person);

        assertEquals(person, mapper.readValue(json, Person.class));
    }

    @Test
    public void testSerializePerson2() throws Exception
    {
        Data date = new Data(28, 7, 2000);

        Person person = new Person("Sergeev", "Sergei", "Sergeevich", date);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(person);

        assertEquals(person, mapper.readValue(json, Person.class));
    }

    @Test
    public void testSerializeFlat1() throws Exception
    {
        Data date1 = new Data(12, 3, 2004);
        Data date2 = new Data(13, 4, 2007);
        Data date3 = new Data(23, 1, 2007);

        Person person1 = new Person("Ivanov", "Ivan", "Ivanovich", date1);
        Person person2 = new Person("Dmitriev", "Dmitrii", "Dmitrievich", date2);
        Person person3 = new Person("Liliev", "Artem", "Vladimirovich", date3);

        List<Person> list = new ArrayList<>(Arrays.asList(person1, person2, person3));

        Flat flat = new Flat(45, 56.2, list);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(flat);

        assertEquals(flat, mapper.readValue(json, Flat.class));
    }

    @Test
    public void testSerializeFlat2() throws Exception
    {
        Data date1 = new Data(2, 5, 2007);
        Data date2 = new Data(7, 4, 2010);
        Data date3 = new Data(1, 4, 1995);

        Person person1 = new Person("Sergeev", "Sergei", "Sergeevich", date1);
        Person person2 = new Person("Andreev", "Dmitrii", "Artemovich", date2);
        Person person3 = new Person("Ivanova", "Angelina", "Vladimirovna", date3);

        List<Person> list = new ArrayList<>(Arrays.asList(person1, person2, person3));

        Flat flat = new Flat(89, 30.4, list);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(flat);

        assertEquals(flat, mapper.readValue(json, Flat.class));
    }

    @Test
    public void testSerializeHouse1() throws Exception
    {
        Data date1 = new Data(12, 3, 2004);
        Data date2 = new Data(13, 4, 2007);
        Data date3 = new Data(2, 5, 2007);
        Data date4 = new Data(7, 4, 2010);

        Person person1 = new Person("Ivanov", "Ivan", "Ivanovich", date1);
        Person person2 = new Person("Dmitriev", "Dmitrii", "Dmitrievich", date2);
        Person person3 = new Person("Sergeev", "Sergei", "Sergeevich", date3);
        Person person4 = new Person("Andreev", "Dmitrii", "Artemovich", date4);

        List<Person> list1 = new ArrayList<>(Arrays.asList(person1, person2));
        List<Person> list2 = new ArrayList<>(Arrays.asList(person3, person4));

        Flat flat1 = new Flat(74, 14.2, list1);
        Flat flat2 = new Flat(85, 23.6, list2);

        List<Flat> l = new ArrayList<>(Arrays.asList(flat1, flat2));

        House house = new House("451261", "Omsk", "Mira", "6b", person1, l);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(house);

        assertEquals(house, mapper.readValue(json, House.class));
    }

    @Test
    public void testSerializeHouse2() throws Exception
    {
        Data date1 = new Data(12, 3, 2004);
        Data date2 = new Data(13, 4, 2007);
        Data date3 = new Data(23, 1, 2007);
        Data date4 = new Data(2, 5, 2007);
        Data date5 = new Data(7, 4, 2010);
        Data date6 = new Data(1, 4, 1995);

        Person person1 = new Person("Ivanov", "Ivan", "Ivanovich", date1);
        Person person2 = new Person("Dmitriev", "Dmitrii", "Dmitrievich", date2);
        Person person3 = new Person("Ivanova", "Anastasia", "Sergeevna", date3);
        Person person4 = new Person("Sergeev", "Sergei", "Sergeevich", date4);
        Person person5 = new Person("Andreev", "Dmitrii", "Artemovich", date5);
        Person person6 = new Person("Dmitrieva", "Angelina", "Artemovna", date6);

        List<Person> list1 = new ArrayList<>(Arrays.asList(person1, person2, person3));
        List<Person> list2 = new ArrayList<>(Arrays.asList(person4, person5, person6));

        Flat flat1 = new Flat(74, 14.2, list1);
        Flat flat2 = new Flat(85, 23.6, list2);

        List<Flat> l = new ArrayList<>(Arrays.asList(flat1, flat2));

        House house = new House("451261", "Omsk", "Mira", "6b", person1, l);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(house);

        assertEquals(house, mapper.readValue(json, House.class));
    }
}