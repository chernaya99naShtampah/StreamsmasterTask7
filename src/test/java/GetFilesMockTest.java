import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class GetFilesMockTest
{
    @Test
    public void testGetFiles1() throws Exception
    {
        Streams stream = new Streams();

        File mockFile = Mockito.spy(new File("..//Streams//src//main//java"));

        String[] arr = new String[]{ "data.bin", "Data.java", "data.txt", "FilenameFilter.java", "Flat.java", "FlatDeserialize.java", "FlatSerialize.java", "House.java", "JacksonSerialize.java", "javak", "javaw", "javax", "javax.bin", "Main.java", "Person.java", "PersonDeserialize.java", "PersonSerialize.java", "ran_data.bin", "ran_data.txt", "serialize.bin", "serialize.ser", "serialize.txt", "Service.java", "Streams.java", "test.bin", "test.txt", "test_ser.txt" };

        when(mockFile.list()).thenReturn(arr);

        List<String> l = new ArrayList<>(Arrays.asList("data.txt", "ran_data.txt", "serialize.txt", "test.txt", "test_ser.txt"));

        verify(mockFile).list();

        assertEquals(l, stream.getFiles(mockFile, "txt"));
    }

    @Test
    public void testGetFiles2() throws Exception
    {
        Streams stream = new Streams();

        File mockFile = Mockito.spy(new File("..//Streams//src//main//java"));

        String[] arr = new String[]{ "data.bin", "Data.java", "data.txt", "FilenameFilter.java", "Flat.java", "FlatDeserialize.java", "FlatSerialize.java", "House.java", "JacksonSerialize.java", "javak", "javaw", "javax", "javax.bin", "Main.java", "Person.java", "PersonDeserialize.java", "PersonSerialize.java", "ran_data.bin", "ran_data.txt", "serialize.bin", "serialize.ser", "serialize.txt", "Service.java", "Streams.java", "test.bin", "test.txt", "test_ser.txt" };

        when(mockFile.list()).thenReturn(arr);

        List<String> l = new ArrayList<>(Arrays.asList("data.bin", "javax.bin", "ran_data.bin", "serialize.bin", "test.bin"));

        verify(mockFile).list();

        assertEquals(l, stream.getFiles(mockFile, "bin"));
    }

    @Test
    public void testGetFiles3() throws Exception
    {
        Streams stream = new Streams();

        File mockFile = Mockito.spy(new File("..//Streams//src//main//java"));

        String[] arr = new String[]{ "data.bin", "Data.java", "data.txt", "FilenameFilter.java", "Flat.java", "FlatDeserialize.java", "FlatSerialize.java", "House.java", "JacksonSerialize.java", "javak", "javaw", "javax", "javax.bin", "Main.java", "Person.java", "PersonDeserialize.java", "PersonSerialize.java", "ran_data.bin", "ran_data.txt", "serialize.bin", "serialize.ser", "serialize.txt", "Service.java", "Streams.java", "test.bin", "test.txt", "test_ser.txt" };

        when(mockFile.list()).thenReturn(arr);

        List<String> l = new ArrayList<>(Arrays.asList("Data.java", "FilenameFilter.java", "Flat.java", "FlatDeserialize.java", "FlatSerialize.java", "House.java", "JacksonSerialize.java", "Main.java", "Person.java", "PersonDeserialize.java", "PersonSerialize.java", "Service.java", "Streams.java"));

        verify(mockFile).list();

        assertEquals(l, stream.getFiles(mockFile, "java"));
    }

    @Test
    public void testGetFiles4() throws Exception
    {
        Streams stream = new Streams();

        File mockFile = Mockito.spy(new File("..//Streams//.idea"));

        String[] arr = new String[]{ ".gitignore", "codeStyles", "compiler.xml", "encodings.xml", "jarRepositories.xml", "misc.xml", "vcs.xml", "workspace.xml" };

        when(mockFile.list()).thenReturn(arr);

        List<String> l = new ArrayList<>(Arrays.asList("compiler.xml", "encodings.xml", "jarRepositories.xml", "misc.xml", "vcs.xml", "workspace.xml"));

        verify(mockFile).list();

        assertEquals(l, stream.getFiles(mockFile, "xml"));
    }

    @Test(expected = Exception.class)
    public void testGetFiles6() throws Exception
    {
        Streams stream = new Streams();

        File mockFile = Mockito.spy(new File("..//Streams//src//main//java"));

        String[] arr = new String[]{ "data.bin", "Data.java", "data.txt", "FilenameFilter.java", "Flat.java", "FlatDeserialize.java", "FlatSerialize.java", "House.java", "JacksonSerialize.java", "javak", "javaw", "javax", "javax.bin", "Main.java", "Person.java", "PersonDeserialize.java", "PersonSerialize.java", "ran_data.bin", "ran_data.txt", "serialize.bin", "serialize.ser", "serialize.txt", "Service.java", "Streams.java", "test.bin", "test.txt", "test_ser.txt" };

        when(mockFile.list()).thenReturn(arr);

        verify(mockFile).list();

        stream.getFiles(mockFile, "");
    }

    @Test
    public void testGetFilesRegex1() throws Exception
    {
        Streams stream = Mockito.spy(new Streams());

        File mockFile = Mockito.spy(new File("..//Streams//src//main//java"));

        List<String> l = new ArrayList<>(Arrays.asList("javak", "C:\\Users\\isupo\\IdeaProjects\\Streams\\..\\Streams\\src\\main\\java\\javak\\javak.txt", "javaw", "C:\\Users\\isupo\\IdeaProjects\\Streams\\..\\Streams\\src\\main\\java\\javaw\\javaw.bin", "C:\\Users\\isupo\\IdeaProjects\\Streams\\..\\Streams\\src\\main\\java\\javax.bin"));

        when(stream.getFilesRegex(mockFile, "java.*", new ArrayList<>())).thenReturn(l);

        verify(mockFile).listFiles();
        verify(stream, times(3)).getFilesRegex(any(), any(), any());

        assertTrue(stream.getFilesRegex(mockFile, "java.*", Mockito.spy(new ArrayList<>())).containsAll(l));
    }

    @Test
    public void testGetFilesRegex2() throws Exception
    {
        Streams stream = Mockito.spy(new Streams());

        File mockFile = Mockito.spy(new File("..//Streams//.idea"));

        List<String> l = new ArrayList<>(Arrays.asList("codeStyles", "C:\\Users\\isupo\\IdeaProjects\\Streams\\..\\Streams\\.idea\\jarRepositories.xml"));

        when(stream.getFilesRegex(mockFile, ".*es.*", new ArrayList<>())).thenReturn(l);

        verify(mockFile).listFiles();
        verify(stream, times(1)).getFilesRegex(any(), any(), any());

        assertTrue(stream.getFilesRegex(mockFile, ".*es.*", Mockito.spy(new ArrayList<>())).containsAll(l));
    }

    @Test(expected = IOException.class)
    public void testGetFilesRegex3() throws Exception
    {
        Streams stream = Mockito.spy(new Streams());

        File mockFile = Mockito.spy(new File("..//Streams//src//main//kotlin"));

        when(stream.getFilesRegex(mockFile, ".*", new ArrayList<>())).thenThrow(IOException.class);
    }
}