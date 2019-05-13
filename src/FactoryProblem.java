

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FactoryProblem {


    private static int solve(int[][] A, int[][] T, int n, int e1, int e2, int x1, int x2){
        int opt = Integer.MAX_VALUE;

        int[][] F = new int[2][n];
        int fstar = Integer.MAX_VALUE;
        int lstar = -1;
        int[][] L = new int[2][n];
        int min = 0;

        F[0][0] = e1 + A[0][0];
        F[1][0] = e2 + A[1][0];

        for(int i = 1; i < n; i++)
        {
            if((F[0][i-1] + A[0][i]) < (F[1][i-1] + T[1][i-1] + A[0][i]))
            {
                min = (F[0][i-1] + A[0][i]);
                L[0][i] = 1;

            }
            else
            {
                min = (F[1][i-1] + T[1][i-1] + A[0][i]);
                L[0][i] = 2;
            }

            F[0][i] = min;

            //2nd part
            if((F[1][i-1] + A[1][i]) < (F[0][i-1] + T[0][i-1] + A[1][i]))
            {
                min = (F[1][i-1] + A[1][i]);
                L[1][i] = 2;

            }
            else
            {
                min = (F[0][i-1] + T[0][i-1] + A[1][i]);
                L[1][i] = 1;
            }

            F[1][i] = min;

        }

        if((F[0][n-1]+x1) < (F[1][n-1]+x2))
        {
            min = (F[0][n-1]+x1);
            lstar = 1;
        }
        else
        {
            min = (F[1][n-1]+x2);
            lstar = 2;
        }


        fstar = min;
//        System.out.println("F* is: " + fstar);
//        System.out.println("L* is: " + lstar);
//
//        System.out.println("F: ");
//        for(int i = 0; i < n; i++)
//        {
//            System.out.print(F[0][i] + " ");
//        }
//        System.out.println();
//        for(int i = 0; i < n; i++)
//        {
//            System.out.print(F[1][i] + " ");
//        }
//        System.out.println();

//        System.out.println("L: ");
//        for(int i = 0; i < n; i++)
//        {
//            System.out.print(L[0][i] + " ");
//        }
//        System.out.println();
//        for(int i = 0; i < n; i++)
//        {
//            System.out.print(L[1][i] + " ");
//        }
//        System.out.println();

        System.out.println("Fastest time is: " + fstar);
        System.out.println();
        System.out.println("The optimal route is:");

        int[] print = new int[n];
        int st = lstar - 1;




        for(int k = n-1; k >=0; k--)
        {
            print[k] = lstar;
            lstar = L[lstar - 1][k];
        }


        for(int g = 0; g < n; g++)
        {
            System.out.println("station " + (g+1) + ", line " + print[g]);
        }

        System.out.println();



        return opt;
    }

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter input file name: ");
        String filename = scan.nextLine();



        File f = new File(filename);


        Scanner file = null;
        try
        {
            file = new Scanner(f);
            int n = file.nextInt();
            int e1 = file.nextInt();
            int e2 = file.nextInt();
            int x1 = file.nextInt();
            int x2 = file.nextInt();

            int[][] A = new int[2][n];
            for(int i = 0; i < n; i ++)
            {
                A[0][i] = file.nextInt();
            }
            for(int i = 0; i < n; i ++)
            {
                A[1][i] = file.nextInt();
            }

            int[][] T = new int[2][n-1];
            for(int i = 0; i < n-1; i ++)
            {
                T[0][i] = file.nextInt();
            }
            for(int i = 0; i < n-1; i ++)
            {
                T[1][i] = file.nextInt();
            }



//            System.out.println(n);
//            System.out.println(e1 + " " + e2);
//            System.out.println(x1 + " " + x2);
//            for(int i = 0; i < n; i ++)
//            {
//                System.out.print(A[0][i] + " ");
//            }
//            System.out.println();
//            for(int i = 0; i < n; i ++)
//            {
//                System.out.print(A[1][i] + " ");
//            }
//            System.out.println();
//            for(int i = 0; i < n-1; i ++)
//            {
//                System.out.print(T[0][i] + " ");
//            }
//            System.out.println();
//            for(int i = 0; i < n-1; i ++)
//            {
//                System.out.print(T[1][i] + " ");
//            }
//            System.out.println();

            solve(A, T, n, e1, e2, x1, x2);

        }
        catch (FileNotFoundException e)
        {
            System.err.println("File not found: " + filename);
        }
    }
}
