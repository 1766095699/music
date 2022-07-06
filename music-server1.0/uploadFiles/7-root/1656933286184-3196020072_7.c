#include<stdio.h>
#include<stdlib.h>

int main(int argc,char **argv)
{
	int c;
	FILE *out;
	out=fopen("my.out","w");
	while((c=fgetc(stdin))!='#'){
		fputc(c,out);
	}
	printf("end\n");
	return 0;
}


