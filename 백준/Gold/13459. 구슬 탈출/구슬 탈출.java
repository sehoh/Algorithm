import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int N, M;
    private static boolean isMovable(int nx, int ny, char[][] board) {
        // 범위 내에 있는지 && 벽이 아닌지
        return (ny >= 0 && ny < N && nx >= 0 && nx < M && board[ny][nx] != '#');
    }

    private static char[][] copyBoard(char[][] board) {
        char[][] result = new char[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, result[i], 0, board[i].length);
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        char[][] board = new char[N][M];

        List<Bead> start = new ArrayList<>();
        Coord target = new Coord(0, 0);



        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
                if(board[i][j] == 'R'){
                    start.add(new Bead(new Coord(j, i), 'R'));
                } else if (board[i][j] == 'O') {
                    target = new Coord(j, i);
                }
            }
        }
        // List<Bead> start의 첫번째 원소에 빨강이 들어가도록 빨강 먼저 넣고,
        // 그 다음에 for문을 다시 돌며 파랑을 넣음
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'B') {
                    start.add(new Bead(new Coord(j, i), 'B'));
                }
            }
        }

        System.out.println(BFS(start, target, board));

    }

    private static int BFS(List<Bead> start, Coord target, char[][] board) {
        int[] dy = {-1, 0, 1, 0};
        int[] dx = {0, 1, 0, -1};
        char[][] copy = copyBoard(board);
        Map<List<Bead>, Boolean> visited = new HashMap<>();

        Queue<List<Bead>> queue = new LinkedList<>();
        queue.add(start);
        visited.put(start, true);

        // 10번 이내에 구멍에 도달할 수 있도록
        int count = 10;

        while (!queue.isEmpty() && count >= 0) {
            int len = queue.size();

            for (int i = 0; i < len; i++) {

                List<Bead> entry = queue.poll();
                Bead red = entry.get(0);
                Bead blue = entry.get(1);

                // 빨간 공이 구멍에 도달하면 1 return
                if (red.coord.equals(target)) return 1;


                outer:
                for (int d = 0; d < 4; d++) {
                    boolean flag1 = true, flag2 = true;
                    // 한 방향으로 계속
                    int cx1 = red.coord.x, cy1 = red.coord.y;
                    int cx2 = blue.coord.x, cy2 = blue.coord.y;

                    int mv1 = 0, mv2 = 0; // 각 구슬이 이동한 거리

                    while (flag1) {
                        // 전진
                        int nx1 = cx1 + dx[d];
                        int ny1 = cy1 + dy[d];

                        if (!isMovable(nx1, ny1, copy)) {
                            flag1 = false;
                        } else {
                            mv1++;
                            cx1 = nx1;
                            cy1 = ny1;
                            if (copy[cy1][cx1] == 'O') break;
                        }
                    }
                    while (flag2) {
                        // 전진
                        int nx2 = cx2 + dx[d];
                        int ny2 = cy2 + dy[d];

                        if (!isMovable(nx2, ny2, copy)) {
                            flag2 = false;
                        } else {
                            mv2++;
                            cx2 = nx2;
                            cy2 = ny2;
                            // 파란공이 구멍에 도달해버리면 while문 바깥의 for문 continue
                            if (copy[cy2][cx2] == 'O') continue outer;
                        }
                    }


                    // 같은 지점이라면 둘 중 하나 후진
                    if (cy1 == cy2 && cx1 == cx2) {
                        if (copy[cy1][cx1] != 'O') {
                            // 같은 지점에 도달했는데 빨간 공이 더 많이 이동했다면
                            // 빨간공이 뒤에 있었고, 파란공이 앞에 있었으니까
                            // 빨간공 후진
                            if (mv1 > mv2) {
                                cx1 -= dx[d];
                                cy1 -= dy[d];
                            }
                            // 파란 공이 더 많이 이동했다면
                            // 파란공이 뒤에 있었고, 빨간공에 앞에 있었으니까
                            // 파란공 후진
                            else {
                                cx2 -= dx[d];
                                cy2 -= dy[d];
                            }
                        }
                    }

                    List<Bead> nextEntry = new ArrayList<>();
                    nextEntry.add(new Bead(new Coord(cx1, cy1), 'R'));
                    nextEntry.add(new Bead(new Coord(cx2, cy2), 'B'));


                    // 방문을 안했다면
                    if (!visited.containsKey(nextEntry)) {
                        queue.add(nextEntry);
                        // 방문했음 표시
                        visited.put(nextEntry, true);
                    }
                }
            }
            count--;
        }
        // while문 다 돌았는데도 구멍에 도달 못하면 return 0
        return 0;
    }

    private static class Bead {
        Coord coord;
        char color;

        public Bead(Coord coord, char color) {
            this.coord = coord;
            this.color = color;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Bead) {
                Bead b = (Bead) o;
                return this.coord.equals(b.coord) && this.color == b.color;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(coord, color);
        }
    }

    private static class Coord {
        int x;
        int y;

        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Coord) {
                Coord c = (Coord) obj;
                return this.x == c.x && this.y == c.y;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return (x + ", " + y).hashCode();
        }
    }
}
