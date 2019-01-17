package leetcode.easy;

/**
 * 返回离远点最大的欧氏距离
 * A robot on an infinite grid starts at point (0, 0) and faces north.
 * The robot can receive one of three possible types of commands:
 *
 * -2: turn left 90 degrees
 * -1: turn right 90 degrees
 * 1 <= x <= 9: move forward x units
 * Some of the grid squares are obstacles.
 *
 * The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1])
 *
 * If the robot would try to move onto them,
 * the robot stays on the previous grid square instead (but still continues following the rest of the route.)
 *
 * Return the square of the maximum Euclidean distance that the robot will be from the origin.
 *
 * Example 1:
 * Input: commands = [4,-1,3], obstacles = []
 * Output: 25
 * Explanation: robot will go to (3, 4)
 *
 * Example 2:
 * Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 * Output: 65
 * Explanation: robot will be stuck at (1, 4) before turning left and going to (1, 8)
 *
 * Note:
 *
 * 0 <= commands.length <= 10000
 * 0 <= obstacles.length <= 10000
 * -30000 <= obstacle[i][0] <= 30000
 * -30000 <= obstacle[i][1] <= 30000
 * The answer is guaranteed to be less than 2 ^ 31.
 */
public class WalkingRobotSimulation {

    public int robotSim(int[] commands, int[][] obstacles) {
        int x = 0, y = 0;
        int r=Integer.MIN_VALUE;
        int d = 1;//1朝上，2朝右，3朝下，4朝左
        for(int c : commands){
            if(c == -1){
                d = d % 4 + 1;
            } else if(c == -2){
                d -= 1;
                if(d==0)d=4;
            } else {
                if(d==1){
                    int minY = Integer.MAX_VALUE;
                    for(int[] o: obstacles){
                       if(o[0] == x && o[1] > y && o[1] <= y+c && minY > o[1]){
                            minY = o[1];
                       }
                    }
                    if(minY != Integer.MAX_VALUE){
                        y = minY - 1;
                    } else {
                        y += c;
                    }
                } else if(d == 2){
                    int minX = Integer.MAX_VALUE;
                    for(int[] o: obstacles){
                        if(o[1] == y && o[0] > x && o[0] <= x+c && minX > o[0]){
                            minX = o[0];
                        }
                    }
                    if(minX != Integer.MAX_VALUE){
                        x = minX - 1;
                    } else {
                        x += c;
                    }
                } else if(d == 3){
                    int maxY = Integer.MIN_VALUE;
                    for(int[] o: obstacles){
                        if(o[0] == x && o[1] < y && o[1] >= y-c && maxY < o[1]){
                            maxY = o[1];
                        }
                    }
                    if(maxY != Integer.MIN_VALUE){
                        y = maxY + 1;
                    } else {
                        y -= c;
                    }
                } else {
                    int maxX = Integer.MIN_VALUE;
                    for(int[] o: obstacles){
                        if(o[1] == y && o[0] < x && o[0] >= x-c && maxX < o[0]){
                            maxX = o[0];
                        }
                    }
                    if(maxX != Integer.MIN_VALUE){
                        x = maxX + 1;
                    } else {
                        x -= c;
                    }
                }
            }
            r = Math.max(r, x*x+y*y);
//            System.out.println("x=" + x + "  y=" + y + " d=" +d);
        }
        return r;
    }

    public static void main(String[] args) {
        WalkingRobotSimulation w = new WalkingRobotSimulation();
        System.out.println(w.robotSim(new int[]{4,-1,3}, new int[][]{}));
    }
}
