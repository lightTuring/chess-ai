package Bitboards;

import java.util.Scanner;

import Rules.IllegalMoveException;

public class Player {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Manipulator.init();
        Bits bit = new Bits();
        Manipulator.makeBoards(bit);
        Manipulator.printImage(bit);

        while(!bit.endOfGame) {
            try {  
                System.out.println(" ");
                int ii = s.nextInt();
                int ji = s.nextInt();
                int i = s.nextInt();
                int j = s.nextInt();
              
                int sqi = 8*ii + ji;
                int sqf = 8*i + j;

                Game foranucleo = new Game(bit);
                foranucleo.move(sqi, sqf, bit);
            }
            catch(IllegalMoveException e) {
                System.err.println("Movimento errado");
            }
        }
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