package main.level3;

// 표현 가능한 이진트리
// https://school.programmers.co.kr/learn/courses/30/lessons/150367
class Prg150367 {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String binary = Long.toBinaryString(numbers[i]);
            String fullBinaryTree = toFullBinaryTree(binary);
            int middle = fullBinaryTree.length() / 2;
            char root = fullBinaryTree.charAt(middle);
            if (root == '0') {
                continue;
            }
            if (canBeBinaryTree(fullBinaryTree)) {
                answer[i] = 1;
            }
        }
        return answer;
    }

    private String toFullBinaryTree(String binary) {
        int leafs = 1;
        int nodes = 0;
        while (nodes < binary.length()) {
            nodes += leafs;
            leafs *= 2;
        }

        int dummy = nodes - binary.length();
        return "0".repeat(dummy) + binary;
    }

    private boolean canBeBinaryTree(String tree) {
        int length = tree.length();
        if (length == 0) {
            return true;
        }
        int middle = length / 2;
        char root = tree.charAt(middle);
        if (root == '0') {
            return !tree.contains("1");
        }
        String leftSub = tree.substring(0, middle);
        String rightSub = tree.substring(middle + 1);
        return canBeBinaryTree(leftSub) && canBeBinaryTree(rightSub);
    }
}
