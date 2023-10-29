import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Streams implements FilenameFilter
{
    public void writeArray(int[] arr, String name) throws Exception
    {
        try (DataOutputStream stream = new DataOutputStream(new FileOutputStream(name)))
        {
            for (int i : arr)
            {
                stream.writeInt(i);
            }
        }
        catch (IOException e)
        {
            throw new Exception("Wrong stream!");
        }
    }

    public int[] readArray(int n, String name) throws Exception
    {
        int[] arr = new int[n];

        try (DataInputStream stream = new DataInputStream(new FileInputStream(name)))
        {
            for (int i = 0; i < arr.length; i++)
            {
                arr[i] = stream.readInt();
            }
        }
        catch (IOException e)
        {
            throw new Exception("Wrong stream!");
        }

        return arr;
    }

    public void writeArraySym(int[] arr, String name) throws Exception
    {
        try (OutputStreamWriter stream = new OutputStreamWriter(new FileOutputStream(name), StandardCharsets.UTF_8))
        {
            for (int i: arr)
            {
                stream.write(i + " ");
            }
        }
        catch (IOException e)
        {
            throw new Exception("Wrong stream!");
        }
    }

    public int[] readArraySym(int n, String name) throws Exception
    {
        int[] arr = new int[n];

        try (InputStreamReader stream = new InputStreamReader(new FileInputStream(name), StandardCharsets.UTF_8))
        {
            StringBuilder str = new StringBuilder();

            int index = 0;
            int elem;

            while (((elem = stream.read()) != -1))
            {
                char symbol = (char) elem;

                if (symbol != ' ')
                {
                    str.append(symbol);
                }
                else
                {
                    if (n == index)
                    {
                        break;
                    }

                    arr[index] = Integer.parseInt(str.toString());
                    index++;

                    str = new StringBuilder();
                }
            }
        }
        catch (IOException e)
        {
            throw new Exception("Wrong stream!");
        }

        return arr;
    }

    public int[] readArrayIndex(int n, int index, String name) throws Exception
    {
        if (index > n || index < 0)
        {
            throw new Exception("Wrong index!");
        }

        int[] arr = new int[n - index];

        try (RandomAccessFile file = new RandomAccessFile(name, "r"))
        {
            file.seek(index * 4L);

            for (int i = 0; i < arr.length; i++)
            {
                arr[i] = file.readInt();
            }
        }
        catch (IOException e)
        {
            throw new Exception("Wrong stream!");
        }

        return arr;
    }

    public List<String> getFiles(File file, String end) throws Exception
    {
        if (file == null)
        {
            throw new Exception("Wrong file!");
        }
        else if ("".equals(end) || end == null)
        {
            throw new Exception("Wrong file extension");
        }

        String[] files = file.list();

        List<String> l = new ArrayList<>();

        for (int i = 0; i < files.length; i++)
        {
            if (files[i].endsWith(end))
            {
                l.add(files[i]);
            }
        }

        return l;
    }

    @Override
    public boolean accept(File file, String regex)
    {
        return file.getName().matches(regex);
    }

    public List<String> getFilesRegex(File file, String regex, List<String> list) throws IOException
    {
        if (!file.exists())
        {
            throw new IOException("File doesn't exist!");
        }
        else if ("".equals(regex) || regex == null)
        {
            throw new IllegalArgumentException("Wrong regex!");
        }
        else if (list == null)
        {
            throw new NullPointerException("List is null!");
        }

        FilenameFilter filter = new Streams();

        if (file.isDirectory())
        {
            File[] temp = file.listFiles();

            if (temp != null)
            {
                for (File name : temp)
                {
                    if (filter.accept(name, regex) && name.isFile())
                    {
                        list.add(name.getAbsolutePath());
                    }
                    else if (filter.accept(name, regex) && name.isDirectory())
                    {
                        list.add(name.getName());
                        getFilesRegex(name, regex, list);
                    }
                }
            }
        }

        return list;
    }

    public void saveHouseCsv(House house)
    {
        if (house == null)
        {
            throw new IllegalArgumentException("Wrong object!");
        }

        try (FileWriter writer = new FileWriter("house_" + house.getNumber() + ".csv"))
        {
            CSVWriter writeCsv = new CSVWriter(writer, ';', CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);

            writeCsv.writeNext(new String[]{ "", "Data about the house" });
            writeCsv.writeNext(new String[]{ "Cadastral number: " + house.getNumber()});
            writeCsv.writeNext(new String[]{ "Address: " + house.getAddressCity() + ", " + house.getAddressStreet() + ", " + house.getAddressNumber() });
            writeCsv.writeNext(new String[]{ "Senior housemate: " + house.getAdmin().getSurname() + " " + house.getAdmin().getName() + " " + house.getAdmin().getPatronymic() });

            writeCsv.writeNext(new String[]{ "", "Data about apartments", "" });
            writeCsv.writeNext(new String[]{ "Number", "Area, sq. m", "Owners" });

            for (int i = 0; i < house.getFlats().size(); i++)
            {
                writeCsv.writeNext(new String[]{ String.valueOf(house.getFlats().get(i).getNumber()), String.valueOf(house.getFlats().get(i).getArea()), house.getFlats().get(i).getNamesList().toString() });
            }

            writeCsv.close();
        }
        catch (IOException e)
        {
            throw new RuntimeException("Wrong writer!");
        }
    }
}
