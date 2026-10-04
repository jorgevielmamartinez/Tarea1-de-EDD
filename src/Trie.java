import java.util.ArrayList;
import java.util.List;

public class Trie {
    private NodoTrie root;
    public Trie() {
        root=new NodoTrie();
    }
    private class NodoTrie {
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
            if(buscar(w)==true){
                System.out.println("La palabra ya se encuentra en el trie");
                return;
            }
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
    public List<String> Autocompletar(String s){
        List<String> PalabrasCompletas=new ArrayList<>();
        if(s==null||s.isEmpty()|| root==null){
            return PalabrasCompletas;
        }
        NodoTrie nodoActual = root;
        for (int i = 0; i < s.length()-1; i++) {
            char letra = s.charAt(i);
            int indice = letra - 'A';
            if (nodoActual.P[indice]==null) {
                return PalabrasCompletas;
            }
            nodoActual=nodoActual.P[indice];
        }
        int ultimaLetra=s.charAt(s.length()-1)-'A';
        if ((nodoActual.B& (1<< ultimaLetra))!=0) {
            PalabrasCompletas.add(s);
        }
        if (nodoActual.P[ultimaLetra]!=null){
            nodoActual=nodoActual.P[ultimaLetra];
            CompletarPalabras(nodoActual,s,PalabrasCompletas);
        }
        return  PalabrasCompletas;
    }
    private void CompletarPalabras(NodoTrie nodoActual, String s, List<String> PalabrasCompletas){
        NodoTrie aux = nodoActual;
        if (aux==null) {
            return;
        }
        for (int i = 0; i < 26; i++) {
            char letra=(char)(i+'A');
            if ((aux.B&(1<<i))!=0) {
                String Palabra=s+letra;
                PalabrasCompletas.add(Palabra);
            }
            if (aux.P[i]!=null) {
                CompletarPalabras(aux.P[i],s+letra,PalabrasCompletas);
            }
        }
        }
    }
