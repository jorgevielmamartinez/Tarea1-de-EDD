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
        int mask;
        NodoTrie[] P=new NodoTrie[26];
        NodoTrie(int mask) {
            this.mask=0;
        }
    }
    public boolean buscar(String w){
        NodoTrie nodo=root;

        for(int i=0;i<w.length();i++){
            char c=w.charAt(i);
            int indice=c-'A';
            if(indice<0|| indice>26){
                return false;
            }

            int aux=1<<indice;
            if((nodo.mask &aux)==0){
                return false;
            }
            return nodo.finPalabra;
        }
    }
}
