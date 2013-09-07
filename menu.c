#include <stdio.h>
#include <string.h>

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


void menu()
{
  int opc;
  while(opc != 3)
  {printf("_____________Chat lenguajes___________\n");
  printf("1)Agregar contacto.\n");
  printf("2)Enviar mensaje.\n");
  printf("3) Salir.\n");
  printf("Ingrese una opcion: ");
  scanf("%i",&opc);
  switch(opc)
  {
     case 1:
          agregarcontacto();           
          break;
     case 2:
          printf("enviar mensaje\n");
          break;
     case 3:
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
