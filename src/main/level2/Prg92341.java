package main.level2;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

// 주차 요금 계산
// https://school.programmers.co.kr/learn/courses/30/lessons/92341
class Prg92341 {

    public int[] solution(int[] fees, String[] records) {
        Fee fee = new Fee(fees);
        Map<String, Car> cars = new TreeMap<>();
        for (int i = 0; i < records.length; i++) {
            StringTokenizer st = new StringTokenizer(records[i]);
            String time = st.nextToken();
            String number = st.nextToken();
            String command = st.nextToken();

            if (!cars.containsKey(number)) {
                cars.put(number, new Car());
            }
            cars.get(number).park(time, command);
        }

        int[] answer = new int[cars.size()];
        int i = 0;
        for (String number : cars.keySet()) {
            answer[i] = fee.calculate(cars.get(number));
            i++;
        }
        return answer;
    }

    private static class Fee {
        int basicTime;
        int basicFee;
        int addTime;
        int addFee;

        Fee(int[] fees) {
            basicTime = fees[0];
            basicFee = fees[1];
            addTime = fees[2];
            addFee = fees[3];
        }

        int calculate(Car car) {
            int parked = car.getParked();
            if (parked <= basicTime) {
                return basicFee;
            }
            int overTime = parked - basicTime;
            return basicFee + caclulateOverFee(overTime);
        }

        int caclulateOverFee(int overTime) {
            int unit = overTime / addTime;
            if (overTime % addTime > 0) {
                unit++;
            }
            return unit * addFee;
        }
    }

    private static class Car {
        LocalTime lastIn;
        LocalTime lastOut;
        long parked;

        Car() {
            parked = 0;
        }

        void park(String time, String command) {
            if ("IN".equals(command)) {
                in(time);
            } else {
                out(time);
            }
        }

        void in(String time) {
            lastIn = toLocalTime(time);
        }

        void out(String time) {
            lastOut = toLocalTime(time);
            parked += Duration.between(lastIn, lastOut).toMinutes();
        }

        int getParked() {
            if (lastOut == null || lastOut.isBefore(lastIn)) {
                out("23:59");
            }
            return (int) parked;
        }

        private LocalTime toLocalTime(String time) {
            StringTokenizer st = new StringTokenizer(time, ":");
            return LocalTime.of(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }
}
