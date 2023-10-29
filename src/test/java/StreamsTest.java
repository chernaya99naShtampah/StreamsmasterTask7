import org.junit.Test;

import java.io.*;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class StreamsTest
{
    @Test
    public void testReadArray1() throws Exception
    {
        Streams s = new Streams();

        int[] arr = { 1, 2, 3, 4, 5 };

        s.writeArray(arr, "test.bin");

        assertArrayEquals(arr, s.readArray(5, "test.bin"));
    }

    @Test
    public void testReadArray2() throws Exception
    {
        Streams s = new Streams();

        int[] arr = { 27, 31, 46, 54, 77, 92 };

        s.writeArray(arr, "test.bin");

        int[] array = { 27, 31, 46 };

        assertArrayEquals(array, s.readArray(3, "test.bin"));
    }

    @Test
    public void testReadArray3() throws Exception
    {
        Streams s = new Streams();

        int[] arr = new int[0];

        s.writeArray(arr, "test.bin");

        assertArrayEquals(arr, s.readArray(0, "test.bin"));
    }

    @Test
    public void testReadArray4() throws Exception
    {
        Streams s = new Streams();

        int[] arr = { -27, 31, 46, -54, 77, 92 };

        s.writeArray(arr, "test.bin");

        int[] array = { -27, 31, 46, -54 };

        assertArrayEquals(array, s.readArray(4, "test.bin"));
    }

    @Test(expected = Exception.class)
    public void testReadArray5() throws Exception
    {
        Streams s = new Streams();

        int[] arr = { 1, 2, 3, 4, 5 };

        s.writeArray(arr, "test.bin");

        s.readArray(6, "test.bin");
    }

    @Test
    public void testReadArraySym1() throws Exception
    {
        Streams s = new Streams();

        int[] arr = { 12, 45, 8, 96, 7 };

        s.writeArraySym(arr, "test.txt");

        assertArrayEquals(arr, s.readArraySym(5, "test.txt"));
    }

    @Test
    public void testReadArraySym2() throws Exception
    {
        Streams s = new Streams();

        int[] arr = { 45, 12, 8, 56, 2, 4 };

        s.writeArraySym(arr, "test.txt");

        int[] array = { 45, 12, 8 };

        assertArrayEquals(array, s.readArraySym(3, "test.txt"));
    }

    @Test
    public void testReadArraySym3() throws Exception
    {
        Streams s = new Streams();

        int[] arr = new int[0];

        s.writeArraySym(arr, "test.txt");

        assertArrayEquals(arr, s.readArraySym(0, "test.txt"));
    }

    @Test
    public void testReadArraySym4() throws Exception
    {
        Streams s = new Streams();

        int[] arr = { 28, -79, 56, 14, 3 };

        s.writeArraySym(arr, "test.txt");

        assertArrayEquals(arr, s.readArraySym(5, "test.txt"));
    }

    @Test(expected = Exception.class)
    public void testReadArraySym5() throws Exception
    {
        Streams s = new Streams();

        int[] arr = { 28, -79, 56, 14, 3 };

        s.writeArraySym(arr, "test.txt");

        s.readArraySym(5, "test1.txt");
    }

    @Test
    public void testReadArrayIndex1() throws Exception
    {
        Streams s = new Streams();

        int[] arr = { 28, -79, 56, 14, 3 };

        s.writeArray(arr, "test.bin");

        int[] array = { 56, 14, 3 };

        assertArrayEquals(array, s.readArrayIndex(5, 2, "test.bin"));
    }

    @Test
    public void testReadArrayIndex2() throws Exception
    {
        Streams s = new Streams();

        int[] arr = { 1, 784, 1256, -46, 123, -7 };

        s.writeArray(arr, "test.bin");

        int[] array = { 1, 784, 1256, -46, 123, -7 };

        assertArrayEquals(array, s.readArrayIndex(6, 0, "test.bin"));
    }

    @Test
    public void testReadArrayIndex3() throws Exception
    {
        Streams s = new Streams();

        int[] arr = { 78, 52, -1, -2, -56, 7489, 1, 0, -56, 456 };

        s.writeArray(arr, "test.bin");

        int[] array = { 7489, 1, 0, -56, 456 };

        assertArrayEquals(array, s.readArrayIndex(10, 5, "test.bin"));
    }

    @Test
    public void testReadArrayIndex4() throws Exception
    {
        Streams s = new Streams();

        int[] arr = { 78, 52, -1, -2, -56, 7489, 1, 0, -56, 456 };

        s.writeArray(arr, "test.bin");

        int[] array = { 456 };

        assertArrayEquals(array, s.readArrayIndex(10, 9, "test.bin"));
    }

    @Test(expected = Exception.class)
    public void testReadArrayIndex5() throws Exception
    {
        Streams s = new Streams();

        int[] arr = { 78, 52, -1, -2, -56, 7489, 1, 0, -56, 456 };

        s.writeArray(arr, "test.bin");

        s.readArrayIndex(10, -1, "test.bin");
    }

    @Test(expected = Exception.class)
    public void testReadArrayIndex6() throws Exception
    {
        Streams s = new Streams();

        int[] arr = { 78, 52, -1, -2, -56, 7489, 1, 0, -56, 456 };

        s.writeArray(arr, "test.bin");

        s.readArrayIndex(10, 11, "test.bin");
    }

    @Test
    public void testGetFiles1() throws Exception
    {
        Streams s = new Streams();

        File f = new File("..//Streams//src//main//java");

        List<String> l = new ArrayList<>(Arrays.asList("data.txt", "ran_data.txt", "serialize.txt", "test.txt", "test_ser.txt"));

        assertEquals(l, s.getFiles(f, "txt"));
    }
    @Test
    public void testGetFiles2() throws Exception
    {
        Streams s = new Streams();

        File f = new File("..//Streams//src//main//java");

        List<String> l = new ArrayList<>(Arrays.asList("data.bin", "javax.bin", "ran_data.bin", "serialize.bin", "test.bin"));

        assertEquals(l, s.getFiles(f, "bin"));
    }

    @Test
    public void testGetFiles3() throws Exception
    {
        Streams s = new Streams();

        File f = new File("..//Streams//src//main//java");

        List<String> l = new ArrayList<>(Arrays.asList("Data.java", "FilenameFilter.java", "Flat.java", "FlatDeserialize.java", "FlatSerialize.java", "House.java", "JacksonSerialize.java", "Main.java", "Person.java", "PersonDeserialize.java", "PersonSerialize.java", "Service.java", "Streams.java"));

        assertEquals(l, s.getFiles(f, "java"));
    }

    @Test
    public void testGetFiles4() throws Exception
    {
        Streams s = new Streams();

        File f = new File("..//Streams//.idea");

        List<String> l = new ArrayList<>(Arrays.asList("compiler.xml", "encodings.xml", "jarRepositories.xml", "misc.xml", "vcs.xml", "workspace.xml"));

        assertEquals(l, s.getFiles(f, "xml"));
    }

    @Test(expected = Exception.class)
    public void testGetFiles5() throws Exception
    {
        Streams s = new Streams();

        s.getFiles(null, "bin");
    }

    @Test(expected = Exception.class)
    public void testGetFiles6() throws Exception
    {
        Streams s = new Streams();

        File f = new File("..//Streams//src//main//java");

        s.getFiles(f, "");
    }

    @Test
    public void testGetFilesRegex1() throws Exception
    {
        Streams s = new Streams();

        File f = new File("..//Streams//.idea");

        List<String> l = new ArrayList<>(Arrays.asList("codeStyles", "C:\\Users\\isupo\\IdeaProjects\\Streams\\..\\Streams\\.idea\\jarRepositories.xml"));
        List<String> tmp = new ArrayList<>();

        assertTrue(s.getFilesRegex(f, ".*es.*", tmp).containsAll(l));
    }

    @Test
    public void testGetFilesRegex2() throws Exception
    {
        Streams s = new Streams();

        File f = new File("..//Streams//src//main//java");

        List<String> l = new ArrayList<>(Arrays.asList("javak", "C:\\Users\\isupo\\IdeaProjects\\Streams\\..\\Streams\\src\\main\\java\\javak\\javak.txt", "javaw", "C:\\Users\\isupo\\IdeaProjects\\Streams\\..\\Streams\\src\\main\\java\\javaw\\javaw.bin", "C:\\Users\\isupo\\IdeaProjects\\Streams\\..\\Streams\\src\\main\\java\\javax.bin"));
        List<String> tmp = new ArrayList<>();

        assertTrue(s.getFilesRegex(f, "java.*", tmp).containsAll(l));
    }

    @Test(expected = IOException.class)
    public void testGetFilesRegex3() throws IOException
    {
        Streams s = new Streams();

        File f = new File("..//Streams//src//main//kotlin");

        List<String> tmp = new ArrayList<>();

        s.getFilesRegex(f, ".*", tmp);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetFilesRegex4() throws IOException
    {
        Streams s = new Streams();

        File f = new File("..//Streams//src//main//java");

        List<String> tmp = new ArrayList<>();

        s.getFilesRegex(f, "", tmp);
    }

    @Test(expected = NullPointerException.class)
    public void testGetFilesRegex5() throws IOException
    {
        Streams s = new Streams();

        File f = new File("..//Streams//src//main//java");

        s.getFilesRegex(f, ".*", null);
    }
}