package Algorithm;

import Rules.Game;

public class ForaNucleo {
    public Game board;
    public GraphBuilder gb;
    
    public ForaNucleo(Game board, GraphBuilder gb) {
        this.board = board;
        this.gb = gb;
    }

    private static void createPlaying(GraphBuilder gb){
        //chama recursao 
    }

    public void getGlobalPlaying(){
        for(todas as peças){
            if(pode jogar){
                createPlaying(new GraphBuilder());
            }
        }
    }
}