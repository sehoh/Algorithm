class Solution {
    static class Count {
        public int zero, one;

        public Count(int zero, int one) {
            this.zero = zero;
            this.one = one;
        }

        public Count addCount(Count other) {
            return new Count(zero + other.zero, one + other.one);
        }
    }
    public Count count(int offsetX, int offsetY, int size, int[][] arr) {
        // 탐색후 쿼드압축
        int h = size / 2;
        for (int x = offsetX; x < offsetX + size; x++) {
            for (int y = offsetY; y < offsetY + size; y++) {
                // 모두다 같은지 체크
                if (arr[x][y] != arr[offsetX][offsetY]) {
                    // 같다면 쿼드 압축
                    return count(offsetX, offsetY, h, arr)
                            .addCount(count(offsetX + h, offsetY, h, arr))
                            .addCount(count(offsetX, offsetY + h, h, arr))
                            .addCount(count(offsetX + h, offsetY + h, h, arr));
                }
            }
        }
        // 이중 for문에서 안걸리면 size = 1 인 정사각형
        if (arr[offsetX][offsetY] == 1) {
            return new Count(0, 1);
        }
        return new Count(1, 0);
    }

    public int[] solution(int[][] arr) {
        Count count = count(0, 0, arr.length, arr);
        return new int[]{count.zero, count.one};
    }
}
