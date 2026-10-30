package io.github.fiikbr.followers;

import java.util.SortedSet;

public record Report(
        SortedSet<String> notFollowingBack,
        SortedSet<String> youDontFollow,
        SortedSet<String> mutuals) {
}