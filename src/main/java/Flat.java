import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonSerialize(using = FlatSerialize.class)
@JsonDeserialize(using = FlatDeserialize.class)
public class Flat implements Serializable
{
    @JsonProperty("number")
    private int number;
    @JsonProperty("area")
    private double area;
    @JsonProperty("list")
    private List<Person> list;

    public Flat()
    {

    }

    public Flat(int number, double area, List<Person> list) throws Exception
    {
        if (number < 0)
        {
            throw new Exception("Wrong number!");
        }
        else if (area < 0)
        {
            throw new Exception("Wrong area!");
        }
        else if (list == null || list.isEmpty())
        {
            throw new Exception("Wrong list!");
        }

        this.number = number;
        this.area = area;

        this.list = list;
    }

    public int getNumber()
    {
        return number;
    }

    public double getArea()
    {
        return area;
    }

    public List<Person> getList()
    {
        return list;
    }

    public List<String> getNamesList()
    {
        List<String> list = new ArrayList<>();

        for (int i = 0; i < getList().size(); i++)
        {
            String str = getList().get(i).getSurname().concat(" " + getList().get(i).getName()).concat(" " + getList().get(i).getPatronymic());
            list.add(str);
        }

        return list;
    }
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

        Flat flat = (Flat) o;

        return number == flat.number && Double.compare(flat.area, area) == 0 && Objects.equals(list, flat.list);
    }


    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    @Override
    public String toString()
    {
        return "Flat = { number = " + number + ", area = " + area + ", list = " + list + " }";
    }
}
