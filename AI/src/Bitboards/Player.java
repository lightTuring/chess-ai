package Bitboards;

import java.util.Scanner;

import Rules.IllegalMoveException;

public class Player {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Manipulator.init();
        Bits bit = new Bits();
        Manipulator.makeBoards(bit);
        

        while(!bit.endOfGame) {
            try {
                Manipulator.printImage(bit);  
                System.out.println(" ");
                int ii = s.nextInt();
                int ji = s.nextInt();
                int i = s.nextInt();
                int j = s.nextInt();
                long a = System.currentTimeMillis();

                int sqi = 8*ii + ji;
                int sqf = 8*i + j;

                Game foranucleo = new Game(bit);
                foranucleo.move(sqi, sqf, bit);
                long b = System.currentTimeMillis();
                System.out.println(b - a);
            }
            catch(IllegalMoveException e) {
                System.err.println("Movimento errado");
            }
        }
        System.out.println("Acabou");
        Manipulator.printImage(bit);  

        s.close();
    }
}
/*
00000001
00000001
00000001
00000001
00000001
00000001
00000001
00000001

00000011
00000011
00000011
00000011
00000011
00000011
00000011
00000011

10000000
10000000
10000000
10000000
10000000
10000000
10000000
10000000

00000000
00000000
00000000
00000000
00000000
00000000
11111111
00000000

00000000
11111111
00000000
00000000
00000000
00000000
00000000
00000000

11111111
11111111
00000000
00000000
00000000
00000000
00000000
00000000

00000000
00000000
00000000
00000000
00000000
00000000
11111111
11111111

00000000
00000000
00000000
00000000
00000000
00000000
00000000
11111111

1000000000000000000000000
11111111
11111111
*/