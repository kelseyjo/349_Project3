import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GameProblem {

    public static void game(int n, int m, int[][] A)
    {
        //output the max score and route
        int[][] S = new int[n][m]; // each S[i][j] = max score starting at this cell
        char[][] R = new char[n][m]; // save info about the choice made in computing the value of respective S[i,j]

        int sum = 0;
        int max = Integer.MIN_VALUE;
        int rowM = -1;
        int colM = -1;

        S[n-1][m-1] = A[n-1][m-1];

        for(int i = n-2; i >=0; i--)
        {
            if(S[i+1][m-1] > 0){
                sum = S[i+1][m-1]; //move down
                R[i][m-1] = 'd';
            }
            else{
                sum = 0; //exit
                R[i][m-1] = 'e';
            }

            S[i][m-1] = sum + A[i][m-1];
            if(S[i][m-1] > max)
            {
                max = S[i][m-1];
                rowM = i;
                colM = m-1;
            }


        }

        for(int j = m-2; j >=0; j--)
        {
            if(S[n-1][j+1] > 0){
                sum = S[n-1][j+1]; //move right
                R[n-1][j] = 'r';
            }
            else{
                sum = 0; //exit
                R[n-1][j] = 'e';
            }

            S[n-1][j] = sum + A[n-1][j];
            if(S[n-1][j] > max)
            {
                max = S[n-1][j];
                rowM = n-1;
                colM = j;
            }

        }

        for(int i = n-2; i >=0; i--)
        {
            for(int j = m-2; j >=0; j--)
            {
                if(S[i+1][j] > S[i][j+1]){
                    sum = S[i+1][j]; //move down
                    R[i][j] = 'd';
                }
                else{
                    sum = S[i][j+1]; //move right
                    R[i][j] = 'r';
                }

                S[i][j] = sum + A[i][j];
                if(S[i][j] > max)
                {
                    max = S[i][j];
                    rowM = i;
                    colM = j;

                }
            }
        }


        //output the max score and route

//        System.out.println("S Matrix:");
//        for(int i = 0; i < S.length; i ++)
//        {
//            for(int j = 0; j < S[0].length; j++)
//            {
//                System.out.print(S[i][j]);
//                System.out.print(" ");
//            }
//            System.out.println();
//        }
        System.out.println("Best score: " + max);
        System.out.print("Best route: ");

        char cur = R[rowM][colM];
        boolean print = true;
        while(cur != 'e') {

            System.out.print("[" + (rowM + 1) + "," + (colM + 1) + "] to ");

            if(cur == 'r')
            {
                colM ++;
                cur = R[rowM][colM];
            }
            else if(cur == 'd')
            {
                rowM ++;
                cur = R[rowM][colM];
            }
            else {
                print = false;
                cur = 'e';
                System.out.println("exit");
            }

        }
        if(print) {
            System.out.print("[" + (rowM + 1) + "," + (colM + 1) + "] to exit");
        }

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
            int n = file.nextInt(); //rows
            int m = file.nextInt(); //cols

            int[][] A = new int[n][m];


            for(int i = 0; i < A.length; i ++)
            {
                for(int j = 0; j < A[0].length; j++)
                {
                    A[i][j] = file.nextInt();
                }
            }
            game(n,m,A);






//            System.out.println("N: " + n);
//
//            System.out.println("M: " + m);
//            System.out.println("A Matrix:");
//            for(int i = 0; i < A.length; i ++)
//            {
//                for(int j = 0; j < A[0].length; j++)
//                {
//                    System.out.print(A[i][j]);
//                    System.out.print(" ");
//                }
//                System.out.println();
//            }




        }
        catch (FileNotFoundException e)
        {
            System.err.println("File not found: " + filename);
        }
    }
}
