import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FlatTest
{
    @Test
    public void testFlat1() throws Exception
    {
        Data date1 = new Data(12, 3, 2004);
        Data date2 = new Data(13, 4, 2007);
        Data date3 = new Data(23, 1, 2007);

        Person person1 = new Person("Ivanov", "Ivan", "Ivanovich", date1);
        Person person2 = new Person("Dmitriev", "Dmitrii", "Dmitrievich", date2);
        Person person3 = new Person("Liliev", "Artem", "Vladimirovich", date3);

        List<Person> list = new ArrayList<>(Arrays.asList(person1, person2, person3));

        Flat flat = new Flat(45, 56.2, list);

        assertTrue(flat.getNumber() == 45 && Double.compare(flat.getArea(), 56.2) == 0 && list.equals(flat.getList()));
    }

    @Test(expected = Exception.class)
    public void testFlat2() throws Exception
    {
        Data date1 = new Data(12, 3, 2004);
        Data date2 = new Data(13, 4, 2007);
        Data date3 = new Data(23, 1, 2007);

        Person person1 = new Person("Ivanov", "Ivan", "Ivanovich", date1);
        Person person2 = new Person("Dmitriev", "Dmitrii", "Dmitrievich", date2);
        Person person3 = new Person("Liliev", "Artem", "Vladimirovich", date3);

        List<Person> list = new ArrayList<>(Arrays.asList(person1, person2, person3));

        Flat flat = new Flat(-41, 24.3, list);
    }

    @Test(expected = Exception.class)
    public void testFlat3() throws Exception
    {
        Flat flat = new Flat(71, 27.3, null);
    }
}