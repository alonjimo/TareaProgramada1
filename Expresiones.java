// Clase NodosLista 
class nodo { 
 // datos amigables para que la Clase Lista Tenga un acceso directo 
     String exp;      
     nodo siguiente; 
   
//Construtor  Crea un nodo del tipo Object 
 nodo (String valor) 
  {  exp =valor; 
     siguiente = null;  //siguiente con valor de nulo 
  } 
   
// Constructor Crea un nodo del Tipo Object y al siguiente nodo de la lista 
nodo (String valor, nodo signodo) 
{   exp = valor; 
    siguiente = signodo; //siguiente se refiere al siguiente nodo 
} 
   
//Retorna el dato que se encuentra en este nodo 
Object getObject(){return exp; } 
   
//Retorna el siguiente nodo 
 nodo getnext() {return siguiente; } 
   
}//Final de la Clase NodosLista 
   
   
//Definici�n de la Clase Lista 
 public class Expresiones 
{ 
  public nodo PrimerNodo;  
   
//Constructor construye una lista vacia con un nombre s 
   
//Retorna True si Lista Vac�a 
 public boolean VaciaLista () {return PrimerNodo == null;}    
   
//Inserta al Final de la Lista 
//Si la lista se encuentra vac�a, el PrimerNodo y el UltimoNodo se refieren al nuevo nodo. Si no, la variable de siguiente de UltimoNodo se refiere al nuevo nodo. 
public void InsertaFinal(String ElemInser) 
{ if ( VaciaLista()) 
     PrimerNodo = new nodo(ElemInser); 
  else
     {nodo aux = this.PrimerNodo; 
      while(aux.siguiente!=null) 
       {aux = aux.siguiente; 
       } 
      nodo nuevo =new nodo (ElemInser); 
      aux.siguiente = nuevo; 
     } 
       
} 
   
public void EliminaFinal(){ 
    if(VaciaLista()){} 
    else if(PrimerNodo.siguiente==null) 
        PrimerNodo = null; 
    else{ 
        nodo aux = this.PrimerNodo; 
        while(aux.siguiente.siguiente!=null){ 
            aux=aux.siguiente; 
        } 
        aux.siguiente = null; 
    } 
} 
   
public void Imprimir() 
{ 
    if (VaciaLista()) 
        System.out.println("vacia"); 
    else{ 
        nodo Actual = PrimerNodo; 
        while(Actual != null){ 
            System.out.print("Expresion: "+Actual.exp+"\n"); 
            Actual = Actual.siguiente; 
        } 
    } 
} 
   
 
// << 2.BuscarElemento en una lista 
public boolean  BuscarElemento(String Elem) 
{   boolean respuesta = false; 
    try
    { 
      nodo aux= this.PrimerNodo; 
      while((aux!=null)&(aux.exp !=Elem)) 
        aux=aux.siguiente; 
      if(aux != null) 
        respuesta=true; 
     } 
     catch(Exception e) 
     { 
          respuesta=false; 
     } 
return respuesta; 
} 
   
void deepCopy(Expresiones lista1){ 
    nodo aux = lista1.PrimerNodo; 
    while (aux != null) { 
        this.InsertaFinal(aux.exp); 
        aux = aux.siguiente; 
    } 
}     
};