import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonSerialize
{
    public String jacksonSer(House house) throws Exception
    {
        if (house == null)
        {
            throw new Exception("Wrong house!");
        }

        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(house);
    }

    public House jacksonDeser(String str) throws Exception
    {
        if ("".equals(str) || str == null)
        {
            throw new Exception("Wrong string!");
        }

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(str, House.class);
    }
}
