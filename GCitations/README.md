## Synopsis

This Java application gets the corresponding Google Citations profile url from a list of authors. Project from the 'Linked Open Data' course at the Universidade Federal de Alagoas - Brazil.

## To run

Add a list of author names (1 name per line): data/pending_completelist.csv

Attention: this list will be modified as the algorithm iterates through it: 

1. in case of success, the author name will be added to 'AUTHORS_GOOGLE.out';
2. In case of failure, to 'NoURLFoundError.out'.  