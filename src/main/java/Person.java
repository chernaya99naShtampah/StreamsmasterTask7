import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Objects;

@JsonSerialize(using = PersonSerialize.class)
@JsonDeserialize(using = PersonDeserialize.class)
public class Person implements Serializable
{
    @JsonProperty("surname")
    private String surname;
    @JsonProperty("name")
    private String name;
    @JsonProperty("patronymic")
    private String patronymic;
    @JsonProperty("date")
    private Data date;

    public Person()
    {

    }
    public Person(String surname, String name, String patronymic, Data date) throws Exception
    {
        if ("".equals(surname) || surname == null || "".equals(name) || name == null || "".equals(patronymic) || patronymic == null)
        {
            throw new Exception("Wrong FIO!");
        }
        else if (date == null)
        {
            throw new Exception("Wrong date!");
        }

        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;

        this.date = date;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getName()
    {
        return name;
    }

    public String getPatronymic()
    {
        return patronymic;
    }

    public Data getDate()
    {
        return date;
    }

    /*public boolean equals(Person obj)
    {
        return this.surname.equals(obj.getSurname()) && this.name.equals(obj.getName()) && this.patronymic.equals(obj.getPatronymic()) && this.date.equals(obj.getDate());
    }*/

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Person person = (Person) o;

        return Objects.equals(surname, person.surname) && Objects.equals(name, person.name) && Objects.equals(patronymic, person.patronymic) && Objects.equals(date, person.date);
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    @Override
    public String toString()
    {
        return "Person = { surname = " + surname + ", name = " + name + ", patronymic = " + patronymic + ", date = " + date + " }";
    }
}
