public class Trie {
    private NodoTrie root;
    public Trie() {
        root=new NodoTrie();
    }
    private class NodoTrie {
        char simbolo;
        boolean finPalabra;
        NodoTrie hijo;
        NodoTrie hermano;
        int B;
        NodoTrie[] P;
        NodoTrie() {
            this.B=0;
            this.P = new NodoTrie[26];
        }
    }
    public boolean buscar(String w){
        NodoTrie nodo=root;
        if(w==null){
            return false;
        }

        for(int i=0;i<w.length();i++){
            char c=w.charAt(i);
            int indice=c-'A';
            if(indice<0|| indice>=26){
                return false;
            }

            if(i==w.length()-1){
                int aux=1<<indice;

                return (nodo.B & aux)!=0;
            }
           if(nodo.P[indice]==null) {
               return false;
           }
           nodo=nodo.P[indice];
           }
            return false;
        }
    public void insert(String w) {
        if (w==null||w.length()==0){
            return;
        }
        for(int i=0;i<w.length();i++){
            int indice=w.charAt(i)-'A';
            if(indice<0|| indice>=26){
                return;
            }
        }
        NodoTrie act=root;
        for (int i = 0; i < w.length(); i++) {
            char c=w.charAt(i);
            int indice=c-'A';
            if (i==w.length()-1){
                act.B=act.B | (1<<indice);
            }else{
                if (act.P[indice]==null){
                    act.P[indice]=new NodoTrie();
                }
                act=act.P[indice];
            }
        }
    }

    public void eliminar(String w) {
        if (!buscar(w)) {
            return;
        }
        NodoTrie nodoActual = root;

        for (int i = 0; i < w.length(); i++) {
            char letra = w.charAt(i);
            int indice = letra - 'A';

            if (i == w.length() - 1) {
                int mascara = ~(1 << indice);
                nodoActual.B = nodoActual.B & mascara;
            } else {
                nodoActual = nodoActual.P[indice];
            }
        }
    }
}
