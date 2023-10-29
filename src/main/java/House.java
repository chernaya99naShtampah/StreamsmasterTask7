import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@JsonSerialize(using = HouseSerialize.class)
@JsonDeserialize(using = HouseDeserialize.class)
public class House implements Serializable
{
    @JsonProperty("number")
    private String number;

    @JsonProperty("addressCity")
    private String addressCity;
    @JsonProperty("addressStreet")
    private String addressStreet;
    @JsonProperty("addressNumber")
    private String addressNumber;
    @JsonProperty("admin")
    private Person admin;
    @JsonProperty("flats")
    private List<Flat> flats;

    public House()
    {

    }

    public House(String number, String addressCity, String addressStreet, String addressNumber, Person admin, List<Flat> flats) throws Exception
    {
        if ("".equals(number) || number == null)
        {
            throw new Exception("Wrong number of house!");
        }
        else if ("".equals(addressCity) || addressCity == null || "".equals(addressStreet) || addressStreet == null || "".equals(addressNumber) || addressNumber == null)
        {
            throw new Exception("Wrong address!");
        }
        else if (admin == null)
        {
            throw new Exception("Wrong admin!");
        }
        else if (flats == null || flats.isEmpty())
        {
            throw new Exception("Wrong list!");
        }

        this.number = number;

        this.addressCity = addressCity;
        this.addressStreet = addressStreet;
        this.addressNumber = addressNumber;

        this.admin = admin;

        this.flats = flats;
    }

    public String getNumber()
    {
        return number;
    }

    public String getAddressCity()
    {
        return addressCity;
    }

    public String getAddressStreet()
    {
        return addressStreet;
    }

    public String getAddressNumber()
    {
        return addressNumber;
    }

    public Person getAdmin()
    {
        return admin;
    }

    public List<Flat> getFlats()
    {
        return flats;
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

        House house = (House) o;

        return Objects.equals(number, house.number) && Objects.equals(addressCity, house.addressCity) && Objects.equals(addressStreet, house.addressStreet) && Objects.equals(addressNumber, house.addressNumber) && Objects.equals(admin, house.admin) && Objects.equals(flats, house.flats);
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    @Override
    public String toString()
    {
        return "House = {" + "number ='" + number + '\'' + ", addressCity ='" + addressCity + '\'' + ", addressStreet ='" + addressStreet + '\'' +
                ", addressNumber ='" + addressNumber + '\'' + ", admin =" + admin + ", flats =" + flats + '}';
    }
}
