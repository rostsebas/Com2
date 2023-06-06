
package proyectocom2;
    
import static proyectocom2.EncrypCesar.NewTex;

public class DecrypCesar {
    static String NewTex,FinTex; 
    
    public DecrypCesar(String NewTex){
        //texto = "Laboratorio de comunicaciones 2, Segundo Semestre 2020.";   
        int salto = 3;
        FinTex =desencriptarCesar(NewTex, salto);  
        System.out.println("Texto descifrado: " + FinTex);
        return;
    }
    
     //desencripdado
    public static String desencriptarCesar(String NewTex, int salto) {
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
    }
}
