public class Trie {
    private NodoTrie root;
    public Trie(){
        root=null;
    }
    private class NodoTrie {
        int B;
        NodoTrie[] P;
        NodoTrie() {
            this.P=new NodoTrie[26];
            this.B=0;
        }
    }
    public void insert(String w) {
       if (w==null||w.length()==0){
           return;
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
}