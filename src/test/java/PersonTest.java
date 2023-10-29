import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest
{
    @Test
    public void testPerson1() throws Exception
    {
        Data date = new Data(12, 3, 2004);

        Person person = new Person("Ivanov", "Ivan", "Ivanovich", date);

        assertTrue("Ivanov".equals(person.getSurname()) && "Ivan".equals(person.getName()) && "Ivanovich".equals(person.getPatronymic())
                && date.equals(person.getDate()));
    }

    @Test(expected = Exception.class)
    public void testPerson2() throws Exception
    {
        Data date = new Data(12, 3, 2004);

        Person person = new Person("Ivanov", "", "Ivanovich", date);
    }

    @Test(expected = Exception.class)
    public void testPerson3() throws Exception
    {
        Person person = new Person("Ivanov", "", "Ivanovich", null);
    }
}