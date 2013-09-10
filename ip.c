#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>

int main()
{
 int sock;
 struct sockaddr_in cliente;
 
 sock = socket(AF_INET,SOCK_STREAM,0);
 if( sock<0)
  {perror("no se creo el socket\n");
   exit(1);
  }
 
 cliente.sin_family = AF_INET;
 cliente.sin_port = htons(5050);
 //cliente.sin_addr.s_addr = inet_addr("192.168.2.104");
 
 char str[INET_ADDRSTRLEN];
 if(inet_pton(AF_INET, "192.16.2.104", &(cliente.sin_addr)) <= 0){
	 printf("Error");
 }
 inet_ntop(AF_INET, &(cliente.sin_addr), str, INET_ADDRSTRLEN);
 printf("%s\n", str);
 
}
