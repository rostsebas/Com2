package proyectocom2;

import java.io.IOException;
import java.util.*;

public class EncrypCesar {
    static String texto,NewTex,FinTex; 
    /*public static void main(String[] args) throws IOException {
        César text;
        text = new César("Cristian Sebastian Martínez Arévalo");
    }*/  
    
    public EncrypCesar(String texto){
        int salto = 3;
        NewTex =cifradoCesar(texto, salto); 
        System.out.println("Texto inicial: " + texto);  
        System.out.println("Texto cifrado: " + NewTex);     
        return;
    }
    
    //cifrado
    public static String cifradoCesar(String texto, int salto) {
        StringBuilder cifrado = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') { //97-122
                if ((texto.charAt(i) + salto) > 'z') {  
                    cifrado.append((char) (texto.charAt(i) + salto - 26));  
                } 
                else {
                    cifrado.append((char) (texto.charAt(i) + salto)); 
                }
            } 
            else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') { //65-90
                if ((texto.charAt(i) + salto) > 'Z') {
                    cifrado.append((char) (texto.charAt(i) + salto - 26));
                } 
                else {
                    cifrado.append((char) (texto.charAt(i) + salto));
                }
            }
            else if (texto.charAt(i) >= ' ' && texto.charAt(i) <= ':') { //32-58 
                if ((texto.charAt(i) + salto) > ':') {
                    cifrado.append((char) (texto.charAt(i) + salto - 26));
                } 
                else {
                    cifrado.append((char) (texto.charAt(i) + salto));
                }
            }
        }
        return cifrado.toString();
    }   
    //desencripdado
   /* public static String desencriptarCesar(String NewTex, int salto) {
        StringBuilder desencriptar = new StringBuilder();
        for (int i = 0; i < NewTex.length(); i++) {
            if (NewTex.charAt(i) >= 'a' && NewTex.charAt(i) <= 'z') {
                if ((NewTex.charAt(i) - salto) < 'a') {
                    desencriptar.append((char) (NewTex.charAt(i) - salto + 26));
                } 
                else {
                    desencriptar.append((char) (NewTex.charAt(i) - salto));
                }
            } 
            else if (NewTex.charAt(i) >= 'A' && NewTex.charAt(i) <= 'Z') {
                if ((NewTex.charAt(i) - salto) < 'A') {
                    desencriptar.append((char) (NewTex.charAt(i) - salto + 26));
                } 
                else {
                    desencriptar.append((char) (NewTex.charAt(i) - salto));
                }
            }
            else if (NewTex.charAt(i) >= ' ' && NewTex.charAt(i) <= ':') {
                if ((NewTex.charAt(i) - salto) < ' ') {
                    desencriptar.append((char) (NewTex.charAt(i) - salto + 26));
                } 
                else {
                    desencriptar.append((char) (NewTex.charAt(i) - salto));
                }
            }
        }
        return desencriptar.toString();
    }*/
}
