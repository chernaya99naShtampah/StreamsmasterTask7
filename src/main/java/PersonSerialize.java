import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class PersonSerialize extends StdSerializer<Person>
{
    public PersonSerialize()
    {
        super(Person.class);
    }

    @Override
    public void serialize(Person person, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException
    {
        generator.writeStartObject();

        generator.writeStringField("fullName", person.getSurname().concat(" " + person.getName()).concat(" " + person.getPatronymic()));
        generator.writeObjectField("date", person.getDate());

        generator.writeEndObject();
    }
}
