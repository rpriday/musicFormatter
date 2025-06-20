import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.*;
import org.jaudiotagger.*;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting...");

        // Disable *all* Jaudiotagger logs:
        Logger.getLogger("org.jaudiotagger").setLevel(Level.OFF);

        // Get file directory from arguments
        if(args.length != 1){
            System.out.println("Usage: java Main <directory>");
            System.exit(1);
        }

        // Open given file and ensure its a directory
        File dir = new File(args[0]);
        if(!dir.isDirectory()){
            System.out.println("Not a directory: " + args[0]);
            System.exit(1);
        }

        // List all entries
        File[] music = dir.listFiles();
        int count = 0;
        if(music != null){
            for(File f : music){
                AudioFile audioFile = null;
                try {
                    audioFile = AudioFileIO.read(f);
                } catch (CannotReadException | InvalidAudioFrameException | ReadOnlyFileException | TagException |
                         IOException e) {
                    continue;
                }
                count++;
                System.out.println(audioFile.getAudioHeader().getFormat());
            }
        }
        System.out.println("Succesfully read: " + count);

    }
}