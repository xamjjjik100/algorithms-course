package education.longestsequence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LongestSequenceTest {

    @Test
    void nullInputs() {
        assertThrows(IllegalArgumentException.class, () -> LongestSequence.ls(null, "a"));
        assertThrows(IllegalArgumentException.class, () -> LongestSequence.ls("a", null));
    }

    @Test
    void emptyInputs() {
        assertThrows(IllegalArgumentException.class, () -> LongestSequence.ls("", "abc"));
        assertThrows(IllegalArgumentException.class, () -> LongestSequence.ls("abc", ""));
        assertThrows(IllegalArgumentException.class, () -> LongestSequence.ls("", ""));
    }

    @Test
    void identicalStrings() {
        assertEquals("sequence", LongestSequence.ls("sequence", "sequence"));
        assertEquals("ABCD", LongestSequence.ls("ABCD", "ABCD"));
    }

    @Test
    void noCommonCharacters() {
        assertEquals("", LongestSequence.ls("ABC", "def"));
        assertEquals("", LongestSequence.ls("XYZ", "123"));
    }

    @Test
    void caseSensitive() {
        assertEquals("", LongestSequence.ls("ABC", "abc"));
        assertEquals("a", LongestSequence.ls("aA", "xa"));
    }

    @Test
    void ABCD_vs_ACBD() {
        assertEquals("ABD", LongestSequence.ls("ABCD", "ACBD"));
    }

    @Test
    void ABCBDAB_vs_BDCABA() {
        assertEquals("BCBA", LongestSequence.ls("ABCBDAB", "BDCABA"));
    }

    @Test
    void cyrillic() {
        assertEquals("вет", LongestSequence.ls("привет", "ветер"));
    }
}
