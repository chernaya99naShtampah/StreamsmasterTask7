import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlatDeserialize extends StdDeserializer<Flat>
{
    public FlatDeserialize()
    {
        super(Flat.class);
    }

    @Override
    public Flat deserialize(JsonParser parser, DeserializationContext context)
    {
        try
        {
            int number = 0;
            double area = 0.0;

            List<String> fio = new ArrayList<>();

            List<Integer> days = new ArrayList<>();
            List<Integer> months = new ArrayList<>();
            List<Integer> years = new ArrayList<>();

            List<Person> list = new ArrayList<>();

            while (parser.nextToken() != JsonToken.END_OBJECT)
            {
                String fieldName = parser.getCurrentName();

                if ("number".equals(fieldName))
                {
                    parser.nextToken();
                    number = parser.getIntValue();
                }
                else if ("area".equals(fieldName))
                {
                    parser.nextToken();
                    area = parser.getDoubleValue();
                }
                else if ("list".equals(fieldName))
                {
                    parser.nextToken();

                    while (parser.nextToken() != JsonToken.END_ARRAY)
                    {
                        String fn = parser.getCurrentName();

                        if ("fullName".equals(fn))
                        {
                            parser.nextToken();
                            fio.add(parser.getValueAsString());
                        }
                        else if ("date".equals(fn))
                        {
                            parser.nextToken();
                        }
                        else if ("day".equals(fn))
                        {
                            parser.nextToken();
                            days.add(parser.getIntValue());
                        }
                        else if ("month".equals(fn))
                        {
                            parser.nextToken();
                            months.add(parser.getIntValue());
                        }
                        else if ("year".equals(fn))
                        {
                            parser.nextToken();
                            years.add(parser.getIntValue());
                        }
                    }
                }
                else
                {
                    throw new IllegalArgumentException("Wrong format of json");
                }
            }

            for (int i = 0; i < fio.size(); i++)
            {
                String s = fio.get(i);

                String[] str = s.split(" ");

                Person p = new Person(str[0], str[1], str[2], new Data(days.get(i), months.get(i), years.get(i)));

                list.add(p);
            }

            return new Flat(number, area, list);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
