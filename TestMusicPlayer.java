import java.io.FileNotFoundException;
import java.io.PrintStream;

public class TestMusicPlayer {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream output = new PrintStream("my-output.txt");
        output.println(output);

        MusicPlayer p = new MusicPlayer();

        output.println("--- 1. Build the playlist ---");
        output.println(p.addSong("s1", "Night Drive", "Artist 1"));
        output.println(p.addSong("s2", "Slow Tide", "Artist 2"));
        output.println(p.addSong("s3", "Copper Sky", "Artist 3"));
        output.println(p.addSong("s4", "Static Bloom", "Artist 4"));
        output.println(p.addSong("s5", "Low Orbit", "Artist 5"));
        output.println(p.addSong("s6", "Glass Rivers", "Artist 6"));
        output.println("duplicate s3: " + p.addSong("s3", "Duplicate", "Artist"));
        output.println("playlist: " + p.showPlaylist());
        output.println("--- 2. Look up songs ---");
        output.println("find s4: " + p.findSong("s4"));
        output.println("find s9: " + p.findSong("s9"));
        output.println("--- 3. Play and move forward ---");
        output.println("play s1: " + p.play("s1"));
        output.println("play s9: " + p.play("s9"));
        output.println("next: " + p.next());
        output.println("next: " + p.next());
        output.println("history: " + p.showHistory());
        output.println("--- 4. Up next ---");
        output.println(p.queueLater("s5"));
        output.println(p.queueLater("s6"));
        output.println(p.queueNext("s1"));
        output.println("queue s9: " + p.queueNext("s9"));
        output.println("up next: " + p.showUpNext());
        output.println("next: " + p.next());
        output.println("now playing: " + p.nowPlaying());
        output.println("history: " + p.showHistory());
        output.println("--- 5. Back button ---");
        output.println("back: " + p.back());
        output.println("up next: " + p.showUpNext());
        output.println("history: " + p.showHistory());
        output.println("--- 6. Remove songs ---");
        output.println("remove s5: " + p.removeSong("s5"));
        output.println("remove current (s3): " + p.removeSong("s3"));
        output.println("remove s9: " + p.removeSong("s9"));
        output.println("playlist: " + p.showPlaylist());
        output.println("up next: " + p.showUpNext());
        output.println("--- 7. Removed songs are skipped ---");
        output.println("next: " + p.next());
        output.println("next: " + p.next());
        output.println("next at end: " + p.next());
        output.println("now playing: " + p.nowPlaying());
        output.println("back: " + p.back());
        output.println("up next: " + p.showUpNext());
        output.println("--- 8. Play counts ---");
        output.println("s1: " + p.playCount("s1"));
        output.println("s3: " + p.playCount("s3"));
        output.println("s6: " + p.playCount("s6"));
        output.println("s5 (removed): " + p.playCount("s5"));
        output.println("--- 9. Empty player ---");
        MusicPlayer empty = new MusicPlayer();
        output.println("next: " + empty.next());
        output.println("back: " + empty.back());
        output.println("now playing: " + empty.nowPlaying());
        output.println("playlist: " + empty.showPlaylist());
    }
}
