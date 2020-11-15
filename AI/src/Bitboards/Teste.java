public class Teste {
    public static void main(String[] args) {
        Bits bit = new Bits();
        bit.makeBoards();
        System.out.println(Long.toBinaryString(bit.above_msb_mask_include(36)));
        //System.out.println(bit.msb(9223372036854775807L) == 4611686018427387904L);
    }
}
