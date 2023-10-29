import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;

public class Service
{
    public static void serializeHouse(House house, String filename) throws Exception
    {
        if (house == null)
        {
            throw new Exception("Wrong house!");
        }

        try (ObjectOutput out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename))))
        {
            out.writeObject(house);
        }
        catch (IOException e)
        {
            throw new Exception("Wrong stream!");
        }
    }

    public static House deserializeHouse(String filename) throws IOException, ClassNotFoundException, ClassCastException
    {
        try (ObjectInput in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename))))
        {
            return (House) in.readObject();
        }
        catch (IOException e)
        {
            throw new IOException("Wrong stream!");
        }
        catch (ClassNotFoundException e)
        {
            throw new ClassNotFoundException("Wrong found class!");
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException("Wrong class cast!");
        }
    }

    public static boolean jsonEquals(String json1, String json2) throws JsonProcessingException
    {
        try
        {
            JSONObject object1 = new JSONObject(json1);
            JSONObject object2 = new JSONObject(json2);
        }
        catch (JSONException e)
        {
            throw new JSONException("This strings is not json!");
        }

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readTree(json1).equals(mapper.readTree(json2));
    }
}
