public class StepTracker {

    MonthData[] monthToData;
    int goalSteps = 10000;

    public StepTracker() {
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    public void setGoalSteps(int goalSteps) {
        this.goalSteps = goalSteps;
        if (goalSteps > 0) {
            this.goalSteps = goalSteps;
            System.out.println("Новая цель по количеству шагов установлена.");
        } else {
            System.out.println("Введите корректное число шагов.");
        }
    }

    public void saveSteps(int month, int day, int steps) {
        monthToData[month].steps[day] = steps;
    }

    public void printStatistic(int month) {
        Converter converter = new Converter();
        int[] steps = monthToData[month].steps;
        int allStepsMonth = 0;
        int maximumSteps = 0;
        int count = 0;
        int bestSeries = 0;

        for (int i = 0; i < steps.length; i++) {
            allStepsMonth += steps[i];
            System.out.print((i + 1) + " день: " + steps[i] + ", ");
            if (maximumSteps < steps[i]) {
                maximumSteps = steps[i];
            }
            if (steps[i] >= goalSteps) {
                count++;
            } else {
                if (count > bestSeries) {
                    bestSeries = count;
                }
                count = 0;
            }
        }

        System.out.println("Общее количество шагов за месяц -" + allStepsMonth);
        System.out.println("Максимальное пройденное количество шагов в этом месяце -" + maximumSteps);
        System.out.println("Среднее количество шагов в день в этом месяце -" + allStepsMonth / 30);
        System.out.println("Пройденная дистанция (в км) в этом месяце -" + converter.toKm(allStepsMonth));
        System.out.println("Сожжённые килокалории за месяц  -" + converter.toKiloCalorie(allStepsMonth));
        System.out.println("Лучшая серия подряд в месяце - " + bestSeries);
    }

    class MonthData {
        int[] steps = new int[30];
    }

    class Converter {

        public double toKm(int steps) {
            double distance = steps * 0.00075;
            return distance;
        }

        public double toKiloCalorie(int steps) {
            int calories = steps * 50;
            double kiloCalories = calories / 1000.0;
            return kiloCalories;
        }
    }
}


