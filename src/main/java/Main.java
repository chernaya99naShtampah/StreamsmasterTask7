import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        int[] arr = { 5, 2, 3, 4, 5 };

        int[] arr2 = { 7, 8, 9, 10, -111 };
        
        Streams s = new Streams();

        s.writeArray(arr, "data.bin");

        System.out.println(Arrays.toString(s.readArray(5, "data.bin")));

        //char[] arrc = "123456".toCharArray();

        int[] arrs = { 45, 12, 8, 56, 2, 4 };

        s.writeArraySym(arrs, "data.txt");

        System.out.println(Arrays.toString(s.readArraySym(3, "data.txt")));

       /* int[] a = s.readArraySym(arrc.length);

        System.out.println(Arrays.toString(a));*/

        s.writeArray(arr2, "data.bin");

        System.out.println(Arrays.toString(s.readArrayIndex(5, 2, "data.bin")));

        File f = new File("..//Streams//src//main//java");

        String[] d = f.list();

        System.out.println(Arrays.toString(d));
        System.out.println(s.getFiles(f, "txt"));

        Data date1 = new Data(12, 3, 2004);
        Data date2 = new Data(13, 4, 2007);
        Data date3 = new Data(23, 1, 2007);
        Data date4 = new Data(13, 4, 2007);

        Person person1 = new Person("Ivanov", "Ivan", "Ivanovich", date1);
        Person person2 = new Person("Dmitriev", "Dmitrii", "Dmitrievich", date2);
        Person person3 = new Person("Liliev", "Artem", "Vladimirovich", date3);
        Person person4 = new Person("Ivanov", "Ivan", "Ivanovich", date3);
        Person person5 = new Person("Liliev", "Artem", "Vladimirovich", date1);
        Person person6 = new Person("Ivanov", "Ivan", "Ivanovich", date2);
        Person person7 = new Person("Dmitriev", "Dmitrii", "Dmitrievich", date3);
        Person person8 = new Person("Liliev", "Artem", "Vladimirovich", date1);
        Person person9 = new Person("Ivanov", "Ivan", "Ivanovich", date4);

        List<Person> list1 = new ArrayList<>(Arrays.asList(person1, person2, person3));
        List<Person> list2 = new ArrayList<>(Arrays.asList(person4, person5, person6));
        List<Person> list3= new ArrayList<>(Arrays.asList(person7, person8, person1));
        List<Person> list4 = new ArrayList<>(Arrays.asList(person4, person5, person9));

        Flat flat1 = new Flat(45, 56.2, list1);
        Flat flat2 = new Flat(74, 24.25, list2);
        Flat flat3 = new Flat(85, 42.6, list3);
        Flat flat4 = new Flat(74, 63.2, list4);

        List<Flat> l = new ArrayList<>(Arrays.asList(flat1, flat2, flat3));
        List<Flat> l2 = new ArrayList<>(Arrays.asList(flat1, flat4, flat3));

        House house = new House("451261", "Omsk", "Goryacheva", "6b", person1, l);

        /*Service.serializeHouse(house, "serialize.txt");
        System.out.println(Service.deserializeHouse("serialize.txt"));*/

        JacksonSerialize j = new JacksonSerialize();

        String json = j.jacksonSer(house);

        System.out.println(json);

        House h = j.jacksonDeser(json);

        System.out.println(h);

        List<String> list = new ArrayList<>();

        System.out.println(s.getFilesRegex(f, "java.*", list));

        House house1 = new House("97842", "Omsk", "Mira", "7d", person2, l);
        House house2 = new House("451261", "Omsk", "Goryacheva", "8b", person1, l);
        House house3 = new House("451261", "Omsk", "Mira", "7d", person2, l2);

        String json1 = j.jacksonSer(house1);
        String json2 = j.jacksonSer(house3);

        System.out.println(json1.equals(json2));
        System.out.println(Service.jsonEquals(json1, json2));

        ObjectMapper mapper = new ObjectMapper();

        String json3 = mapper.writeValueAsString(person1);

        System.out.println(json3);

        Person p = mapper.readValue(json3, Person.class);

        System.out.println(p);

        String json4 = mapper.writeValueAsString(flat1);

        System.out.println(json4);

        Flat fl = mapper.readValue(json4, Flat.class);

        System.out.println(fl);

        String json5 = mapper.writeValueAsString(house);

        System.out.println(json5);

        House hs = mapper.readValue(json5, House.class);

        System.out.println(hs);
        System.out.println(json5);
        System.out.println(p.equals(person1));
        System.out.println(fl.equals(flat1));
        System.out.println(hs.equals(house));

        s.saveHouseCsv(house);
        s.saveHouseCsv(house1);
    }
}