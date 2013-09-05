#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <string.h>
#include <stdlib.h>

int main()
{

 int sock;
 char datosrecibidos[1024];    //arreglo para recibir los datos   
 struct sockaddr_in servidor; //struct obligatorio   para puertos y almacenar direcciones
 int var1,misock;
 sock = socket(AF_INET, SOCK_STREAM, 0);       
 if(sock ==-1)
  {
   perror("no se creo el socket");
   exit(1);
  }
 else
    {printf("se creo el socket\n");
    }

 servidor.sin_family = AF_INET;         
 servidor.sin_port = htons(9005);     
 servidor.sin_addr.s_addr = INADDR_ANY;

 if (bind(sock,(struct sockaddr *)&servidor,sizeof(servidor)))
 {
  perror("fallo el bind");
  exit(1);
 }

 listen(sock,5);

 while(1)
 {
  misock = accept(sock, (struct sockaddr *)0,0);
  if (misock == -1)
   {perror("error al aceptar");
   }
  else
  {
   memset(datosrecibidos,0,sizeof(datosrecibidos));
   if((var1= recv(misock,datosrecibidos,sizeof(datosrecibidos),0))<0)
     { 
       perror("mensaje de error\n");
     }
   else
       {if(var1==0)
          {
           printf("terminando conexion\n");
          }
        else
           {           
             printf("mensaje: %s\n",datosrecibidos);
           }
        printf("llego el mensaje (var1 = %d)\n",var1);
        close(misock);
       }


  }

 }


return 0;  
}

