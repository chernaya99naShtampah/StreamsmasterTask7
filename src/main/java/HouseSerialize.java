import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class HouseSerialize extends StdSerializer<House>
{
    public HouseSerialize()
    {
        super(House.class);
    }

    @Override
    public void serialize(House house, JsonGenerator generator, SerializerProvider serializerProvider) throws IOException
    {
        generator.writeStartObject();

        generator.writeStringField("number", house.getNumber());

        generator.writeStringField("addressCity", house.getAddressCity());
        generator.writeStringField("addressStreet", house.getAddressStreet());
        generator.writeStringField("addressNumber", house.getAddressNumber());

        generator.writeObjectField("admin", house.getAdmin());

        generator.writeFieldName("flats");
        generator.writeStartArray();

        for (Flat flat: house.getFlats())
        {
            generator.writeObject(flat);
        }

        generator.writeEndArray();
        generator.writeEndObject();
    }
}

