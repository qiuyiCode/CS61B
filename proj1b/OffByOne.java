public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int l = x - 'a';
        int r = y - 'a';
        return l - r == 1 || r - l == 1 ? true : false;
    }
}
