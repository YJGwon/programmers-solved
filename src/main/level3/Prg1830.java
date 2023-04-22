package main.level3;

// 브라이언의 고민
// https://school.programmers.co.kr/learn/courses/30/lessons/1830
class Prg1830 {

    private static final String INVALID = "invalid";
    private static final char BLANK = ' ';

    public String solution(String sentence) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < sentence.length() - 1) {
            char cur = sentence.charAt(idx);
            if (cur == BLANK) {
                return INVALID;
            }
            if (Character.isUpperCase(cur)) {
                char next = sentence.charAt(idx + 1);
                if (Character.isUpperCase(next)) {
                    // 다음이 대문자 -> 해당 글자 하나로 한 단어 취급
                    sb.append(cur).append(BLANK);
                    idx++;
                    continue;
                }
                int lastIdxOfNext = sentence.lastIndexOf(next);
                if (lastIdxOfNext == idx + 1 || (sentence.charAt(idx + 3) == next && lastIdxOfNext != idx + 3)) {
                    // 현재 글자에 반드시 규칙 1 적용
                    if (lastIdxOfNext == sentence.length() - 1) {
                        return INVALID;
                    }
                    String result = extract1(sentence, next, idx, lastIdxOfNext + 1);
                    if (INVALID.equals(result)) {
                        return INVALID;
                    }
                    sb.append(result).append(BLANK);
                    idx = lastIdxOfNext + 2;
                } else {
                    // 규칙 1 아니어도 됨 -> 다음으로 넘기기
                    sb.append(cur).append(BLANK);
                    idx++;
                }
            } else {
                // 반드시 규칙 2 적용
                int lastIdxOfCur = sentence.lastIndexOf(cur);
                if (lastIdxOfCur - idx < 2) {
                    return INVALID;
                }
                String result = extract2(sentence, cur, idx, lastIdxOfCur);
                if (INVALID.equals(result)) {
                    return INVALID;
                }
                sb.append(result).append(BLANK);
                idx = lastIdxOfCur + 1;
            }
        }
        if (idx == sentence.length() - 1) {
            char lastChar = sentence.charAt(idx);
            if (Character.isLowerCase(lastChar) || lastChar == BLANK) {
                return INVALID;
            }
            sb.append(lastChar);
        }

        return sb.toString().trim();
    }

    private String extract1(String sentence, char c, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= end - start; i++) {
            // i % 2가 0이면 대문자
            // i % 2가 1이면 c
            char cur = sentence.charAt(start + i);
            if (i % 2 == 1) {
                if (cur != c) {
                    return INVALID;
                }
            } else {
                if (Character.isLowerCase(cur) || cur == BLANK) {
                    return INVALID;
                }
                sb.append(cur);
            }
        }
        return sb.toString();
    }

    private String extract2(String sentence, char c, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < end - start; i++) {
            char cur = sentence.charAt(start + i);
            if (Character.isLowerCase(cur)) {
                if (i != 2 || sentence.lastIndexOf(cur) != end - 2) {
                    return INVALID;
                }
                // 규칙 1 함께 적용된 경우
                return extract1(sentence, cur, start + 1, end - 1);
            }
            if (cur == BLANK) {
                return INVALID;
            }
            sb.append(cur);
        }
        return sb.toString();
    }
}
