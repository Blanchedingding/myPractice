package test.refactor;

public class DelegateObject {
    static int rob2(Rob rob1) {
        int n = rob1.getNums().length;
        if (n <= 0) return 0;
        int rob = 0, notRob = 0;
        for (int i : rob1.getNums()) {
            int t = notRob;
            notRob = Math.max(rob, notRob);
            rob = t + i;
        }
        return Math.max(rob, notRob);
    }
}