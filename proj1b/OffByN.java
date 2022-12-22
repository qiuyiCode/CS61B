public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int N) {
        this.N = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int l = x - 'a';
        int r = y - 'a';
        return l - r == this.N || r - l == this.N ? true : false;
    }
}
