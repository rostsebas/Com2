#include <SoftwareSerial.h>   // Incluimos la librería  SoftwareSerial  
SoftwareSerial BT(10,11);    // Definimos los pines RX y TX del Arduino conectados al BT
String entrada, texto, cifrado, descifrado, binarios,textoHamming, recepcion, dato, codigo1, codigo2, activo, led;
String bin_paridad = "00000000000";
int valor,salto=3,valor2,a,b;
const int VERDE=2;
const int ROJO=3;

void setup() {
  BT.begin(9600);       // Inicializamos el UART para el BT
  Serial.begin(9600);   // Inicializamos  el puerto serie  
  pinMode(VERDE,OUTPUT);
  pinMode(ROJO,OUTPUT);
}

//Procesos del Bt
void eventosBT(){
  while(BT.available()){
      char letra = BT.read();
      entrada += letra;  
      digitalWrite(VERDE,LOW);
      digitalWrite(ROJO,LOW);        
  }
}

void analisisBT(){
  if(entrada.length()>0){
    valor = entrada.toInt();
    valor = map(valor,0,100,0,255);
    }
  texto = entrada;
  entrada = ""; 
}


//Hamming
void Hamming(String texto){
  String str = texto;
  const int largo = int(str.length());
  int columna = 0;
  int bin[largo][11];
  char caracter[largo-1];
  for(int i=0; i<largo; i++){
    caracter[i] =str.charAt(i);
    binarios = String(int(str[i]),BIN);
    int data=0;
    if(binarios.length()<7){
      binarios = '0'+binarios;
      }
    for(int j = 0; j<11; j++){
      if (j==0||j==1||j==3||j==7){
        bin_paridad[j] =  '0';
        }
      else {
        bin_paridad[j] = binarios[data];
        data++;
        } 
      }
    Paridad1(bin_paridad);
    Paridad2(bin_paridad);
    Paridad3(bin_paridad);
    Paridad4(bin_paridad);
       
    for(int m = 0; m< bin_paridad.length();m++){
      bin[columna][m]= int(bin_paridad[m]);
      }
     columna++;   
    }
    textoHamming=caracter;
}

void Paridad1(String Binario){
  int paridad=0;
  for(int i=0; i<Binario.length(); i+=2){
    if (Binario[i]=='1'){
      paridad++;
      }
    }
  if(paridad%2!=0){
    bin_paridad[0] = '1';
    }
  }
  
void Paridad2(String Binario){
  int paridad=0;
  for(int i=1;i<Binario.length();i+=4){
    for(int j = i; j<i+2; j++){
      if(Binario[j]=='1'){
        paridad ++;
        }
      }
    }
  if(paridad%2!=0){
    bin_paridad[1] = '1';
    }
  }
  
void Paridad3(String Binario){
  int paridad=0;
  for(int i=3;i<Binario.length();i+=6){
    for(int j = i; j<i+3; j++){
      if(Binario[j]=='1'){
        paridad ++;
        }
      }
    }
  if(paridad%2!=0){
    bin_paridad[3] = '1';
    }
  }
  
void Paridad4(String Binario){
  int paridad = 0;
  for(int i=7;i<Binario.length();i++){
    if(Binario[i]=='1'){
     paridad ++;
     }
    }
  if(paridad%2!=0){
    bin_paridad[7] = '1';
    }
  }
  
//Cesar
String descencriptarCesar(String texto, int salto) {
  for (int i = 0; i < texto.length(); i++) {
    if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') { //97-122
      if ((texto.charAt(i) + salto) > 'z') {  
        cifrado.concat((char) (texto.charAt(i) + salto - 26));  
      } 
      else {
        cifrado.concat((char) (texto.charAt(i) + salto)); 
      }
    } 
    else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') { //65-90
      if ((texto.charAt(i) + salto) > 'Z') {
        cifrado.concat((char) (texto.charAt(i) + salto - 26));
      } 
      else {
        cifrado.concat((char) (texto.charAt(i) + salto));
      }
    }
    else if (texto.charAt(i) >= ' ' && texto.charAt(i) <= ':') { //32-58 
      if ((texto.charAt(i) + salto) > ':') {
        cifrado.concat((char) (texto.charAt(i) + salto - 26));
      } 
      else {
        cifrado.concat((char) (texto.charAt(i) + salto));
      }
    }
  }
  return cifrado;  
}  

//Hill
void descencriptarHill(String cadena){
  const int fila = 3;//matriz llave
  const int columna = 3;//matriz llave
  const int longitud_cadena = int(cadena.length());
  int matriz_llave_inversa[fila][columna]={{42,64,14},{60,67,42},{8,13,60}};
  int y[fila];
  int multiplicacion[fila];
  int vec_final[longitud_cadena];
  int cont = 0;
  int cont_k=0;
  int k =0;//Recorrido de cadena en base a las columnas de la matriz
  while(cont< longitud_cadena){
    for (int i=0; i< fila; i++) {
      k=cont_k;
      for(int j=0; j<columna; j++){
        int codigo = int(cadena[k])-32;
        multiplicacion[j] = matriz_llave_inversa[i][j] *codigo ; 
        k++;
      }
     y[i] = multiplicacion[0]+multiplicacion[1]+multiplicacion[2];
   }
    cont_k=k;
    for  (int i=0; i< 3; i++){
    vec_final[cont] = (y[i]%95)+32;
    cont++; 
    }
  }
  for(int i=0; i<longitud_cadena; i++){
    char caracter =vec_final[i]; 
    descifrado = String(descifrado + caracter);
    }
}

void Limpiar(){
  texto="";
  textoHamming="";
  cifrado= ""; 
  descifrado="";
  activo="";
  led="";
}
       
void loop() {
  recepcion = Serial.read();
  if(recepcion.equals("97")){
    dato = "a";
  }
  else if(recepcion.equals("98")){
    dato = "b";
  }
  else if(recepcion.equals("99")){
    led = "1";
  } 
  else if(recepcion.equals("100")){
    led = "2";
  }  
  eventosBT();
  analisisBT();
  Hamming(texto);
  delay(500);
  if(dato.equals("a")){
    descencriptarCesar(textoHamming,salto); 
    a = cifrado.length();
    if(a>15){
      activo = "Cesar"; 
      codigo1="xy"; 
    }
  }
  else if(dato.equals("b")){
    descencriptarHill(textoHamming);
    b = descifrado.length();
    if(b>15){
      activo = "Hill"; 
      codigo2="f";
    }
  }  
  delay(500);  
  if(activo.equals("Cesar")){ 
    if(dato.equals("a")){
      Serial.print(codigo1);
    }
  }
  else if(activo.equals("Hill")){ 
    if(dato.equals("b")){
      Serial.print(codigo2);
    }
  } 
  if(led.equals("1")){
    digitalWrite(ROJO,LOW);
    digitalWrite(VERDE,HIGH);  
  }
  else if(led.equals("2")){
    digitalWrite(VERDE,LOW);
    digitalWrite(ROJO,HIGH);     
  }
  Limpiar();
}
