import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.util.ArrayList;
import java.util.List;

public class HouseDeserialize extends StdDeserializer<House>
{
    public HouseDeserialize()
    {
        super(House.class);
    }

    @Override
    public House deserialize(JsonParser parser, DeserializationContext context)
    {
        try
        {
            int size = 0;

            List<Integer> sizes = new ArrayList<>();

            String number = "";
            String adCity = "";
            String adStreet = "";
            String adNumber = "";

            String surname = "";
            String name = "";
            String patronymic = "";

            int day = 0;
            int month = 0;
            int year = 0;

            List<Integer> numbers = new ArrayList<>();
            List<Double> areas = new ArrayList<>();

            List<String> fio = new ArrayList<>();

            List<Integer> days = new ArrayList<>();
            List<Integer> months = new ArrayList<>();
            List<Integer> years = new ArrayList<>();

            List<Flat> flats = new ArrayList<>();

            while (parser.nextToken() != JsonToken.END_OBJECT)
            {
                String fieldName = parser.getCurrentName();

                if ("number".equals(fieldName))
                {
                    parser.nextToken();
                    number = parser.getText();
                }
                else if ("addressCity".equals(fieldName))
                {
                    parser.nextToken();
                    adCity = parser.getText();
                }
                else if ("addressStreet".equals(fieldName))
                {
                    parser.nextToken();
                    adStreet = parser.getText();
                }
                else if ("addressNumber".equals(fieldName))
                {
                    parser.nextToken();
                    adNumber = parser.getText();
                }
                else if ("admin".equals(fieldName))
                {
                    parser.nextToken();

                    while (parser.nextToken() != JsonToken.END_OBJECT)
                    {
                        String fn = parser.getCurrentName();

                        if ("fullName".equals(fn))
                        {
                            parser.nextToken();

                            String[] str = parser.getValueAsString().split(" ");

                            surname = str[0];
                            name = str[1];
                            patronymic = str[2];
                        }
                        else if ("date".equals(fn))
                        {
                            parser.nextToken();
                        }
                        else if ("day".equals(fn))
                        {
                            parser.nextToken();
                            day = parser.getIntValue();
                        }
                        else if ("month".equals(fn))
                        {
                            parser.nextToken();
                            month = parser.getIntValue();
                        }
                        else if ("year".equals(fn))
                        {
                            parser.nextToken();
                            year = parser.getIntValue();
                        }
                    }

                    parser.nextToken();
                }
                else if ("flats".equals(fieldName))
                {
                    parser.nextValue();

                    while (parser.nextToken() != JsonToken.END_ARRAY)
                    {
                        String f = parser.getCurrentName();

                        if ("number".equals(f))
                        {
                            parser.nextToken();
                            numbers.add(parser.getIntValue());
                        }
                        else if ("area".equals(f))
                        {
                            parser.nextToken();
                            areas.add(parser.getDoubleValue());
                        }
                        else if ("list".equals(f))
                        {
                            parser.nextValue();

                            while (parser.nextToken() != JsonToken.END_ARRAY)
                            {
                                String fName = parser.getCurrentName();

                                if ("fullName".equals(fName))
                                {
                                    parser.nextToken();
                                    fio.add(parser.getValueAsString());
                                    size++;
                                }
                                else if ("date".equals(fName))
                                {
                                    parser.nextValue();
                                }
                                else if ("day".equals(fName))
                                {
                                    parser.nextToken();
                                    days.add(parser.getIntValue());
                                }
                                else if ("month".equals(fName))
                                {
                                    parser.nextToken();
                                    months.add(parser.getIntValue());
                                }
                                else if ("year".equals(fName))
                                {
                                    parser.nextToken();
                                    years.add(parser.getIntValue());
                                }
                            }

                            sizes.add(size);
                            size = 0;
                        }
                    }
                }
                else
                {
                    throw new IllegalArgumentException("Wrong format of json");
                }
            }

            for (int i = 0; i < numbers.size(); i++)
            {
                List<Person> person = new ArrayList<>();

                for (int j = 0; j < sizes.get(i); j++)
                {
                    String s = fio.get(0);

                    String[] str = s.split(" ");

                    Person p = new Person(str[0], str[1], str[2], new Data(days.get(0), months.get(0), years.get(0)));

                    person.add(p);

                    fio.remove(0);
                    days.remove(0);
                    months.remove(0);
                    years.remove(0);
                }

                Flat flat = new Flat(numbers.get(i), areas.get(i), person);
                flats.add(flat);
            }

            Person admin = new Person(surname, name, patronymic, new Data(day, month, year));

            return new House(number, adCity, adStreet, adNumber, admin, flats);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}

