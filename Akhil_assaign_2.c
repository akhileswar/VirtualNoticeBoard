#include <stdio.h>
#include <stdlib.h>
int main()
{   
	char keywrdstr[30];
	char filename[15];
	FILE *keywrd = fopen("reserved_words.txt", "r");
	
	printf("Enter the filename  \n");
    scanf("%s", filename);
    	printf("keywords in the file\n\n");                     
    	
    	
    	
    	
    	
	while(fscanf(keywrd,"%s",keywrdstr)==1)
	{
	    FILE *filepointer = fopen(filename,"r");
	    char str_in_cfile[30];
	    int num=0;
	    
	    
	    
	    while(fscanf(filepointer,"%s",str_in_cfile)==1){
	    	
	    	if(!strcmp(keywrdstr,str_in_cfile))
	    	num++;
		}
		
		
		
		
		
		if(num!=0){
			
			
			printf("%s - %i\n",keywrdstr,num);  
		}
		fclose(filepointer);   
	}
	fclose(keywrd);
	return 0;
}
