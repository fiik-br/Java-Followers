package io.github.fiikbr.followers;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;

import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AnalyzerTest {

    private final Analyzer analyzer = new Analyzer();

    // teste de comportamento para verificar se as listas estão corretas.
    @Test
    void compTheThreeLists(){
        Report report = analyzer.analyze(
                Set.of("alice", "bob", "carol"),
                Set.of("bob", "carol", "dave"));

        assertEquals(Set.of("dave"), report.notFollowingBack());
        assertEquals(Set.of("alice"), report.youDontFollow());
        assertEquals(Set.of("bob", "carol"), report.mutuals());


    }
    // Teste de alteração das entradas
    @Test
    void doesNotMutateInputs(){
        Set<String> followers = new HashSet<>(Set.of("alice", "bob"));
        Set<String> following = new HashSet<>(Set.of("bob", "carol"));

        analyzer.analyze(followers, following);

        assertEquals(Set.of("alice", "bob"), followers);
        assertEquals(Set.of("bob", "carol"), following);
    }
    @Test
    void everythingIsMutualWhenSetsAreEqual(){
        Report report = analyzer.analyze(
                Set.of("alice", "bob"),
                Set.of("alice", "bob"));
        assertTrue(report.notFollowingBack().isEmpty());
        assertTrue(report.youDontFollow().isEmpty());
        assertEquals(Set.of("alice", "bob"), report.mutuals());
    }
    @Test
    void handlesEmptySets(){
        Report report = analyzer.analyze(Set.of(), Set.of());

        assertTrue(report.notFollowingBack().isEmpty());
        assertTrue(report.youDontFollow().isEmpty());
        assertTrue(report.mutuals().isEmpty());
    }
    @Test
    void handlesEmptyFollowers(){
        Report report = analyzer.analyze(Set.of(), Set.of("alice", "bob"));

        assertEquals(Set.of("alice", "bob"), report.notFollowingBack());
        assertTrue(report.youDontFollow().isEmpty());
        assertTrue(report.mutuals().isEmpty());
    }
    @Test
    void handlesEmptyFollowing(){
        Report report = analyzer.analyze(Set.of("alice", "bob"), Set.of());

        assertTrue(report.notFollowingBack().isEmpty());
        assertEquals(Set.of("alice", "bob"), report.youDontFollow());
        assertTrue(report.mutuals().isEmpty());
    }
    @Test
    void returnsListsSortedAlphabetically(){
        Report report = analyzer.analyze(
                Set.of("zoe", "alice"),
                Set.of("carol", "bob"));

        assertEquals(List.of("bob", "carol"),
                new ArrayList<>(report.notFollowingBack()));
        assertEquals(List.of("alice", "zoe"),
                new ArrayList<>(report.youDontFollow()));
    }
}