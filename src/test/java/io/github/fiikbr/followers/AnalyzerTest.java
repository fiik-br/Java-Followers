package io.github.fiikbr.followers;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AnalyzerTest {

    private final Analyzer analyzer = new Analyzer();

    @Test
    void compLists(){
        Report report = analyzer.analyze(
                Set.of("alice", "bob", "carol"),
                Set.of("bob", "carol", "dave"));

        assertEquals(Set.of("dave"), report.notFollowingBack());
        assertEquals(Set.of("alice"), report.youDontFollow());
        assertEquals(Set.of("bob", "carol"), report.mutuals());
    }
}