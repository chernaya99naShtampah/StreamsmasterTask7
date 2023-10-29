import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class FlatSerialize extends StdSerializer<Flat>
{
    public FlatSerialize()
    {
        super(Flat.class);
    }

    @Override
    public void serialize(Flat flat, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException
    {
        generator.writeStartObject();

        generator.writeNumberField("number", flat.getNumber());
        generator.writeNumberField("area", flat.getArea());

        generator.writeFieldName("list");
        generator.writeStartArray();

        for (Person person: flat.getList())
        {
            generator.writeObject(person);
        }

        generator.writeEndArray();
        generator.writeEndObject();
    }
}
