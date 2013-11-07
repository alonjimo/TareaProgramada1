#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>

int main()
{
 int sock;
 struct sockaddr_in cliente;
 char datosenviados[1024];
 struct hostent *hp;

 sock = socket(AF_INET,SOCK_STREAM,0);
 if( sock<0)
  {perror("no se creo el socket\n");
   exit(1);
  }
 else
    {printf("se creo el socket\n");
    }

 cliente.sin_family = AF_INET;         
 cliente.sin_port = htons(9005);
     

 
 hp = gethostbyname ("172.16.1.129");
 if(hp==0)
 {
  perror("fallo conectar con el host\n");
  close(sock);
  exit(1);
 }
 

 if(connect(sock,(struct sockaddr *)&cliente,sizeof(cliente))<0)
 {
  perror("conexion fallida\n");
  close(sock);
  exit(1);
 }
 
 printf("digite su mensaje: ");
 scanf("%s",datosenviados); 

 if(send(sock,datosenviados,sizeof(datosenviados),0)<0)
  {perror("envio fallido\n");
   close(sock);
   exit(1);
  }
 else
{
 printf("enviado con exito \n");
 close(sock);
}

return 0;
}
