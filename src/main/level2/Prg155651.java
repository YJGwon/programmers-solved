package main.level2;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.PriorityQueue;

// 호텔 대실
// https://school.programmers.co.kr/learn/courses/30/lessons/155651
class Prg155651 {
    public int solution(String[][] book_time) {
        PriorityQueue<Booking> bookings = new PriorityQueue<>();
        for (int i = 0; i < book_time.length; i++) {
            bookings.offer(Booking.of(book_time[i]));
        }

        int answer = 1;
        PriorityQueue<LocalTime> endTimes = new PriorityQueue<>();
        endTimes.offer(bookings.poll().end);

        while (!bookings.isEmpty()) {
            Booking current = bookings.poll();
            if (!current.start.isBefore(endTimes.peek())) {
                endTimes.poll();
            } else {
                answer++;
            }
            endTimes.offer(current.end);
        }

        return answer;
    }

    private static class Booking implements Comparable<Booking> {
        LocalTime start;
        LocalTime end;

        private Booking(LocalTime start, LocalTime end) {
            this.start = start;
            if (!end.isBefore(LocalTime.of(23, 50))) {
                this.end = LocalTime.of(23, 59);
            } else {
                this.end = end.plusMinutes(10);
            }
        }

        static Booking of(String[] rawBookTime) {
            return new Booking(timeOf(rawBookTime[0]), timeOf(rawBookTime[1]));
        }

        private static LocalTime timeOf(String rawTime) {
            String[] rawHourAndMinute = rawTime.split(":");
            return LocalTime.of(Integer.parseInt(rawHourAndMinute[0]), Integer.parseInt(rawHourAndMinute[1]));
        }

        @Override
        public int compareTo(Booking o) {
            return Comparator.comparing((Booking b) -> b.start)
                    .thenComparing((Booking b) -> b.end)
                    .compare(this, o);
        }
    }
}
