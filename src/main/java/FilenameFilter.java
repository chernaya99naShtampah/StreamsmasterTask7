import java.io.File;

public interface FilenameFilter
{
    boolean accept(File file, String regex);
}
