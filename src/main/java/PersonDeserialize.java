import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class PersonDeserialize extends StdDeserializer<Person>
{
    public PersonDeserialize()
    {
        super(Person.class);
    }

    @Override
    public Person deserialize(JsonParser parser, DeserializationContext context)
    {
        try
        {
            String surname = "";
            String name = "";
            String patronymic = "";

            int day = 0;
            int month = 0;
            int year = 0;

            while (parser.nextToken() != JsonToken.END_OBJECT)
            {
                String fieldName = parser.getCurrentName();

                if ("fullName".equals(fieldName))
                {
                    parser.nextToken();
                    String[] str = parser.getValueAsString().split(" ");

                    surname = str[0];
                    name = str[1];
                    patronymic = str[2];
                }
                else if ("date".equals(fieldName))
                {
                    parser.nextToken();
                }
                else if ("day".equals(fieldName))
                {
                    parser.nextToken();
                    day = parser.getIntValue();
                }
                else if ("month".equals(fieldName))
                {
                    parser.nextToken();
                    month = parser.getIntValue();
                }
                else if ("year".equals(fieldName))
                {
                    parser.nextToken();
                    year = parser.getIntValue();
                }
                else
                {
                    throw new IllegalArgumentException("Wrong format of json");
                }
            }

            return new Person(surname, name, patronymic, new Data(day, month, year));
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }
}
