public class Trie {
    private NodoTrie root;
    public Trie() {
        root=null;
    }
    private class NodoTrie {
        char simbolo;
        boolean finPalabra;
        NodoTrie hijo;
        NodoTrie hermano;
        int mask=0*80000000;
        NodoTrie(char s,boolean finPalabra,NodoTrie hijo,NodoTrie hermano,int mask) {
            this.simbolo=s;
            this.finPalabra=finPalabra;
            this.hijo=hijo;
            this.hermano=hermano;
            this.mask=mask;
        }
    }
}
