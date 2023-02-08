package ru.kovalev.employee.hierarchy;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Hierarchy<K, V> {
    private final Map<K, HierarchyNode<K, V>> index;
    private final HierarchyNode<K, V> tree;

    public HierarchyNode<K, V> findNode(K key) {
        return index.get(key);
    }

    /**
     * Calculate length of the longest path from root node to leaf in hierarchy
     * using non-recursive (iterative DFS) with LinkedList as Stack
     *
     * @return maximum depth, including root
     */
    public int maxDepth() {
        Deque<HierarchyNode<K, V>> stack = new LinkedList<>();
        stack.push(tree);
        HierarchyNode<K, V> deepest = tree;

        Deque<Integer> depths = new LinkedList<>();
        int depth = 1;
        depths.push(depth);

        while (!stack.isEmpty()) {
            HierarchyNode<K, V> current = stack.pop();
            int currentDepth = depths.pop();

            if (currentDepth > depth) {
                depth = currentDepth;
                deepest = current;
            }
            currentDepth += 1;
            for (HierarchyNode<K, V> child : current.children) {
                stack.push(child);
                depths.push(currentDepth);
            }
        }

        printPath(deepest);
        return depth;
    }

    private void printPath(HierarchyNode<K, V> deepest) {
        List<HierarchyNode<K, V>> pathBackward = new ArrayList<>();
        while (deepest.parentId != null) {
            pathBackward.add(deepest);
            deepest = index.get(deepest.parentId);
        }
        System.out.println(pathBackward.stream()
                .map(hn -> hn.value)
                .map(Object::toString)
                .collect(Collectors.joining(" <- ")));
    }

    @RequiredArgsConstructor
    @Getter
    public static class HierarchyNode<K, V> {
        private final K parentId;
        private final V value;
        private final List<HierarchyNode<K, V>> children = new ArrayList<>();

        public void addChild(HierarchyNode<K, V> child) {
            children.add(child);
        }
    }
}
