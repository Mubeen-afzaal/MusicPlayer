import java.io.FileNotFoundException;
import java.io.PrintStream;

public class MusicPlayerExtraCredit {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream output = new PrintStream("EC-output.txt");
        output.println(output);

        MusicPlayer p = new MusicPlayer();

        p.addSong("s1", "Night Drive", "Artist 1");
        p.addSong("s2", "Slow Tide", "Artist 2");
        p.addSong("s3", "Copper Sky", "Artist 3");

        //EC Method 1 repeatMode()
        output.println("--- Repeat Mode ---");
        p.setRepeat(true);
        p.play("s3");

        output.println("Next: " + p.next());
        output.println("Now Playing: " + p.nowPlaying());

        //EC Method 2 topSongs()
        output.println("--- Top Songs ---");

        p.play("s1");
        p.play("s2");
        p.play("s3");

        output.println("Top 2: " + p.topSongs(2));
    }

}
