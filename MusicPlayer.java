import java.util.*;

public class MusicPlayer {

    public static class Song {
        public final String id;
        public final String title;
        public final String artist;

        public Song(String id, String title, String artist) {
            this.id = id;
            this.title = title;
            this.artist = artist;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    private static class Node {
        Song song;
        Node prev;
        Node next;
        Node(Song song) {
            this.song = song;
        }
    }

    private Node head;
    private Node tail;
    private final Map<String, Node> library = new HashMap<>(); //Library - HashMap
    private final Deque<Song> upNext = new ArrayDeque<>(); //UpNext - Deque
    private final Stack<Song> history = new Stack<>(); // History - Stack
    private final Map<String, Integer> playCounts = new HashMap<>(); //PlayCounts - HashMap
    private Song current;

    //Method # 1 addSong(id, title, artist)
    public boolean addSong(String id, String title, String artist) {
        if (library.containsKey(id)) return false;
        Node node = new Node(new Song(id, title, artist));
        if (tail == null) head = node;
        else { tail.next = node; node.prev = tail; }
        tail = node;
        library.put(id, node);
        return true;
    }

    //Method # 2 findSong(id)
    public Song findSong(String id) {
        Node node = library.get(id);
        return node == null ? null : node.song;
    }

    //Method # 3 showPlaylist()
    public List<String> showPlaylist() {
        List<String> titles = new ArrayList<>();
        for (Node node = head; node != null; node = node.next) titles.add(node.song.title);
        return titles;
    }

    //start(song) - updates the currently playing song & keep track of how many times it has been played.
    private Song start(Song song) {
        current = song;
        playCounts.merge(song.id, 1, Integer::sum);
        return song;
    }

    //Method # 4 play(id)
    public Song play(String id) {
        Song song = findSong(id);
        if (song == null) {
            return null;
        }
        if (current != null) {
            history.push(current);
        }
        return start(song);
    }

    //Method # 5 nowPlaying()
    public Song nowPlaying() {
        return current;
    }

    //Method # 6 playCount(id)
    public int playCount(String id) {
        return playCounts.getOrDefault(id, 0);
    }

    //Method # 7 queueLater(id)
    public boolean queueLater(String id) {
        Song song = findSong(id);
        if (song == null) {
            return false;
        }
        upNext.addLast(song);
        return true;
    }

    //Method # 8 queueNext(id)
    public boolean queueNext(String id) {
        Song song = findSong(id);
        if (song == null) {
            return false;
        }
        upNext.addFirst(song);
        return true;
    }

    //Method # 9 showUpNext()
    public List<String> showUpNext() {
        List<String> titles = new ArrayList<>();
        for (Song song : upNext) {
            if (library.containsKey(song.id)) {
                titles.add(song.title);
            }
        }
        return titles;
    }

    //Method # 10 next()
    public Song next() {
        Song song = null;
        while (!upNext.isEmpty()) {
            Song available = upNext.pollFirst();
            if (library.containsKey(available.id)) {
                song = available;
                break;
            }
        }

        if (song == null) {
            Node node = current == null ? head : library.get(current.id).next;
            if (node != null) {
                song = node.song;
            }
        }
        if (song == null) {
            return null;
        }
        if (current != null) {
            history.push(current);
        }
        return start(song);
    }

    //Method # 11 back()
    public Song back() {
        Song song = null;
        while (!history.isEmpty()) {
            Song available = history.pop();
            if (library.containsKey(available.id)) {
                song = available;
                break;
            }
        }
        if (song == null) {
            return null;
        }
        if (current != null) {
            upNext.addFirst(current);
        }
        return start(song);
    }

    //Method # 12 showHistory()
    public List<String> showHistory() {
        List<String> titles = new ArrayList<>();
        for (int i = history.size() - 1; i >= 0; i--) {
            Song song = history.get(i);
            if (library.containsKey(song.id)) {
                titles.add(song.title);
            }
        }
        return titles;
    }

    //Method # 13 removeSong(id)
    public boolean removeSong(String id) {
        Node node = library.get(id);
        if (node == null || (current != null && current.id.equals(id))) {
            return false;
        }
        if (node.prev == null) {
            head = node.next;
        } else {
            node.prev.next = node.next;
        }
        if (node.next == null) {
            tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }
        library.remove(id);
        playCounts.remove(id);
        return true;
    }
}
/*
 * Structures: custom doubly linked playlist; HashMap library;
 * ArrayDeque up-next deque; history stack; HashMap play counts.
 */