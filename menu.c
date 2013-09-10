#include <stdio.h>
#include <string.h>

void intervenir(char carac[100], int puerto, int ip);

struct contacto{
	char usuario[15];
	int puerto;
	char dir_Ip [15];
};

void leerarchivo(struct contacto aux){
	char nuevo [60];
	char puerto_aux[50];
	sprintf(puerto_aux,"%d",aux.puerto);
	strcpy(nuevo,aux.usuario);
	strcat(nuevo,",");
	strcat(nuevo,puerto_aux);
	strcat(nuevo,",");
	strcat(nuevo,aux.dir_Ip);
	strcat(nuevo,"\n");
	FILE *fp;
	fp = fopen("Contactos.txt", "a+");
	fputs(nuevo,fp);
	fclose(fp);
}

void agregarcontacto()
{

		struct contacto var;
		printf("Inserte el nombre de usuario: ");
		scanf("%s",var.usuario);
		printf("Inserte el puerto a utilizar: ");
		scanf("%d",&(var.puerto));
		printf("Inserte la direccion IP del contacto: ");
		scanf("%s",var.dir_Ip);
		printf("La informacion del contacto nuevo es: %s %d %s \n", var.usuario,var.puerto,var.dir_Ip);
		leerarchivo(var);

                

}



char eliminarcontacto()
{

char contacto[15];
char cadena[15];
int valor;
int valor2;
char caden;
int modificado = 0;
int valoracion=0;
char adena[100];
printf("introduzca nombre del contacto a eliminar: ");
scanf("%s",contacto);
FILE *archivo;
FILE *dest;
archivo=fopen("Contactos.txt","r");// abre para leer
dest = fopen ("aux.txt", "w");// archivo a modificar
if (archivo ==NULL)
{
printf("\Error de apertura del archivo.\n\n");
}
while (!feof(archivo)){

valoracion=fscanf(archivo,"%[a-zA-Z],%d,%d\n",cadena,&valor,&valor2);


if (strcmp (cadena, contacto) != 0)
{ 
  
  char buffer[50];
  char buf[50];
  sprintf( buffer, "%d", valor );
  strcat(cadena, ",");
  strcat(cadena, buffer);
  strcat(cadena, ",");
  sprintf( buf, "%d", valor2 );
  strcat(cadena, buf);
  strcat(cadena, "\n");
  fputs (cadena, dest);
  

}
if (strcmp (cadena, contacto) == 0)
{ printf("contacto eliminado");
 }



}
fclose(archivo);
fclose(dest);
remove ("Contactos.txt"); //borramos el fichero de origen
rename("aux.txt","Contactos.txt");// aca renombramos el archivo al original
}// fin de la funcionalidad





void enviarmensaje()
{

char contacto[15];
char cadena[15];
int valor;
int valor2;
char caden;
int modificado = 0;
int valoracion=0;
char adena[100];
printf("introduzca nombre del contacto al que desea enviar el  mensaje: ");
scanf("%s",contacto);
FILE *archivo;
FILE *dest;
archivo=fopen("Contactos.txt","r");// abre para leer
dest = fopen ("aux.txt", "w");// archivo a modificar
if (archivo ==NULL)
{
printf("\Error de apertura del archivo.\n\n");
}
while (!feof(archivo)){

valoracion=fscanf(archivo,"%[a-zA-Z],%d,%d\n",cadena,&valor,&valor2);
if (strcmp (cadena, contacto) == 0)
{ 
  
intervenir(cadena,valor,valor2);  
  

}




}
fclose(archivo);
fclose(dest);
}// fin de la funcionalidad




void intervenir(char carac[100], int puerto, int ip)

{
printf("%s",carac); 
printf("%d",puerto);
printf("%d",ip); 

}




































void menu()
{ 
  int opc;
  while(opc != 3)
  {printf("_____________Chat lenguajes___________\n");
  printf("1)Agregar contacto.\n");
  printf("2)Enviar mensaje.\n");
  printf("3)Eliminarcontacto.\n");
  printf("4) Salir.\n");
  printf("5)Lista de Contactos.\n");
  
  printf("Ingrese una opcion: ");
  scanf("%i",&opc);
  switch(opc)
  {
     case 1:
          agregarcontacto();           
          break;
     case 2:
          enviarmensaje();
          break;
     case 3:
          eliminarcontacto();
          break;
     case 4:
          printf("saliendo\n");
          break;
     default: 
              printf("Opcion invalida.\n");
              break;                            
              
  }
}
  



}

int main()
{
menu();
return 0;	
}
