package com.alexm.java11.practice.t3.interview.task;

/**
 * @author AlexM
 **/
class Solution {
    class Holder {
        int row = 0;
        int col = 0;
        int k;
        int solutions = 0;
    }

    public int solution(int K, int[][] A) {
        // write your code in Java SE 11
        print(A);
        final Holder holder = new Holder();
        holder.k = K;
        test1(A, holder);
        print(A);
        return holder.solutions;
    }

    void test1(int[][] A, Holder holder) {
        //is space free ?
        if (A[holder.row][holder.col] == 0) {
            System.out.println("row: " + holder.row + " col: " + holder.col + " Empty space");
            // is a house near this place in K distance
            if (isAHouseNear(A, holder) && !isAMarketNear(A, holder)) {
                // mark as place for market
                holder.solutions++;
                //just mark as possible market
                A[holder.row][holder.col] = 2;
            }
        }
        //not free - move on
        if (holder.col == A[holder.row].length - 1) {
            holder.col = 0;
            if (holder.row == A.length - 1) {
                // here is the last row
                return;
            } else {
                holder.row++;
            }
        } else {
            holder.col++;
        }


        test1(A, holder);
    }

    private boolean isAMarketNear(int[][] A, Holder holder) {
        return isSomethingNear(A, holder, 2);
    }

    // 1 house, 2 market
    private boolean isSomethingNear(int[][] A, Holder holder, int something) {
        boolean result = false;
        for (int i = 1; i <= 2; i++) {
            if (isNotOutIfRange(A, holder.row + i, holder.col)) {
                if (A[holder.row + i][holder.col] == something) {
                    result = true;
                }
            }

            if (isNotOutIfRange(A, holder.row - i, holder.col)) {
                if (A[holder.row - i][holder.col] == something) {
                    result = true;
                }
            }

            if (isNotOutIfRange(A, holder.row, holder.col + i)) {
                if (A[holder.row][holder.col + i] == something) {
                    result = true;
                }
            }

            if (isNotOutIfRange(A, holder.row, holder.col - i)) {
                if (A[holder.row][holder.col - i] == something) {
                    result = true;
                }
            }

            if (isNotOutIfRange(A, holder.row - i, holder.col - i)) {
                if (A[holder.row - i][holder.col - i] == something) {
                    result = true;
                }
            }
        }
        return result;
    }

    private boolean isAHouseNear(int[][] A, Holder holder) {
        return isSomethingNear(A, holder, 1);
    }

    private boolean isNotOutIfRange(int[][] a, int row, int col) {
        try {
            int val = a[row][col];
            return true;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    void print(int[][] A) {
        for (int row = 0; row < A.length; row++) {
            for (int col = 0; col < A[row].length; col++) {
                System.out.print(A[row][col] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] A = new int[3][4];
        A[0] = new int[]{0, 0, 0, 0};
        A[1] = new int[]{0, 0, 1, 0};
        A[2] = new int[]{1, 0, 0, 1};
        new Solution().solution(2, A);
    }
}
