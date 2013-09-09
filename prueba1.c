5#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>


int main()
{
 int sock,bytesrecibidos;
 struct sockaddr_in cliente;
 char datosenviados[2048];
 char datosrecibidos[2048];
 char datosrecibidos1[2048];
 const char * const mezcla = "\033[1;35m ";
 const char * const normal = "\033[1;32m";   
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
 cliente.sin_addr.s_addr = INADDR_ANY;
 //inet_addr("172.16.1.129"); //Conexión compu-compu
 bzero(&(cliente.sin_zero),8);
     

 
 //hp = gethostbyname ("10.42.0.1");
 //if(hp==0)
 //{
  //perror("fallo conectar con el host\n");
  //close(sock);
  //exit(1);
 //}

 
 if(connect(sock,(struct sockaddr *)&cliente,sizeof(cliente))<0)
 {
  perror("conexion fallida\n");
  close(sock);
  exit(1);
 }
 if(fork())
          {

while(1){
recv(sock,datosrecibidos,sizeof(datosrecibidos),0);
printf("%s mensaje: %s %s \n",mezcla,datosrecibidos,normal);

}
}

 else{
      while(1){
//printf("digite su mensaje: ");
gets(datosenviados);
if(send(sock,datosenviados,sizeof(datosenviados),0)<0)
               {
perror("envio fallido\n");
close(sock);
exit(1);
}
printf("enviado con exito \n");
}

     }

return 0;
}
		 
