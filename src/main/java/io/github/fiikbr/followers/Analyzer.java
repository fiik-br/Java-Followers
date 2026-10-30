package io.github.fiikbr.followers;

import java.util.Set;
import java.util.TreeSet;

public final class Analyzer {
    public Report analyze(Set<String> followers, Set<String> following) {
        var notFollowingBack = new TreeSet<>(following);
        notFollowingBack.removeAll(followers);
        var youDontFollow = new TreeSet<>(followers);
        youDontFollow.removeAll(following);
        var mutuals = new TreeSet<>(following);
        mutuals.retainAll(followers);
        return new Report(notFollowingBack, youDontFollow, mutuals);
    }
}