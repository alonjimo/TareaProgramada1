#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <arpa/inet.h>

main(){
	int port;
	char term;
	if(scanf("%d%c", &port, &term) != 2 || term != '\n')
		printf("failure\n");
	else
		printf("valid integer followed by enter key\n");
	
}

